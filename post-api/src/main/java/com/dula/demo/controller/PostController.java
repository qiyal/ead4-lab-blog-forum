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

    private final static String publicApi = "/public";
    private final static String privateApi = "/private";

    @GetMapping(publicApi + "/all")
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping(publicApi + "/user/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(this.postService.getPostsByUserId(userId));
    }

    @GetMapping(publicApi + "/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok().body(this.postService.getPostById(postId));
    }

    @PostMapping(publicApi + "/getPostIds")
    public ResponseEntity<PostList> getPostByIds(@RequestBody List<Long> postIds) {
        return ResponseEntity.ok().body(this.postService.getPostByIds(postIds));
    }

    @PostMapping(privateApi + "/create")
    public void createPost(@RequestBody Post post) {
        this.postService.createPost(post);
    }

    @GetMapping(publicApi + "/searchByTitle/{title}")
    public ResponseEntity<List<Post>> searchByTitle(@PathVariable("title") String title) {
        return ResponseEntity.ok(postService.searchLikeByTitle(title));
    }

    @GetMapping(publicApi + "/getByUsername/{username}")
    public ResponseEntity<List<Post>> searchByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(postService.getPostsByUsername(username));
    }

}
