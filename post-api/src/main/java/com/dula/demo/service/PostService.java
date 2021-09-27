package com.dula.demo.service;

import com.dula.demo.entity.Post;
import com.dula.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepo;

    public List<Post> getPostsByUserId(Long userId) {
        return this.postRepo.findByUserId(userId);
    }

    public Post getPostById(Long postId) {
        return this.postRepo.getById(postId);
    }

    public void createPost(Post post) {
        this.postRepo.save(post);
    }
}
