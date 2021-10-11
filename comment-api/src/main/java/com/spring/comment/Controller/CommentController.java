package com.spring.comment.controller;

import com.spring.comment.entity.Comment;
import com.spring.comment.model.CommentList;
import com.spring.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(this.commentService.getCommentsByUserId(userId));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long commentId) {
        return ResponseEntity.ok().body(this.commentService.getCommentById(commentId));
    }

    @PostMapping("/getCommentIds")
    public ResponseEntity<CommentList> getCommentByIds(@RequestBody List<Long> commentIds) {
        return ResponseEntity.ok().body(this.commentService.getCommentByIds(commentIds));
    }

    @PostMapping("")
    public void createComment(@RequestBody Comment comment) {
        this.commentService.createComment(comment);
    }
}
