package com.dula.demo.service;

import com.dula.demo.entity.Post;
import com.dula.demo.model.Comment;
import com.dula.demo.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CommentService {
    @Autowired
    private final RestTemplate restTemplate;

    @HystrixCommand(
              fallbackMethod = "getPostCommentsFallback",
              threadPoolKey = "getCommentById",
              threadPoolProperties = {
                        @HystrixProperty(name="coreSize", value="100"),
                        @HystrixProperty(name="maxQueueSize", value="50"),
              })
    public Comment getCommentById(Long commentId) {
        return restTemplate.getForObject("http://comment-api-app/comment/" + commentId, Comment.class);
    }

    public Comment getCommentByIdFallback(Long commentId) {
        Comment comment = new Comment();
        comment.setId(0L);
        comment.setText("Unknown comment text");
        comment.setAuthor(new User());
        comment.setPost(new Post());
        return comment;
    }
}
