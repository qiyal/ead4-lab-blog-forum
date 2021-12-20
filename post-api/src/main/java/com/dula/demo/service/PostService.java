package com.dula.demo.service;

import com.dula.demo.entity.Post;
import com.dula.demo.model.Comment;
import com.dula.demo.model.PostList;
import com.dula.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepo;

    @Autowired
    private final RestTemplate restTemplate;

    public List<Post> getPostsByUserId(Long userId) {
        return this.postRepo.findByUserId(userId);
    }

    public Post getPostById(Long postId) {
        return this.postRepo.getById(postId);
    }

    public void createPost(Post post) {
        this.postRepo.save(post);
    }

    public PostList getPostByIds(List<Long> postIds) {
        return new PostList(postRepo.getPostsByIdIn(postIds));
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        ResponseEntity<Comment[]> response = this.restTemplate.getForEntity(
                  "http://comment-api-app/comment/post/" + postId,
                  Comment[].class
        );
        Comment[] comments = response.getBody();

        return Arrays.asList(comments != null ? comments : new Comment[0]);
    }

    public List<Post> getAll() {
        return postRepo.findAll();
    }

}
