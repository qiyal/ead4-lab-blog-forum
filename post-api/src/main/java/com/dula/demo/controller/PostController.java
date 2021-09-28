package com.dula.demo.controller;

import com.dula.demo.entity.Post;
import com.dula.demo.model.PostList;
import com.dula.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(this.postService.getPostsByUserId(userId));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok().body(this.postService.getPostById(postId));
    }

    @PostMapping("/getPostIds")
    public ResponseEntity<PostList> getPostByIds(@RequestBody List<Long> postIds) {
        return ResponseEntity.ok().body(this.postService.getPostByIds(postIds));
    }

    @PostMapping("")
    public void createPost(@RequestBody Post post) {
        this.postService.createPost(post);
    }

}
