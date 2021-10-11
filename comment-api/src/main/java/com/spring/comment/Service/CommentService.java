package com.spring.comment.Service;

import com.spring.comment.Entity.Comment;
import com.spring.comment.Model.CommentList;
import com.spring.comment.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getCommentsByUserId(Long userId) {
        return this.commentRepository.findByUserId(userId);
    }

    public Comment getCommentById(Long commentId) {
        return this.commentRepository.getById(commentId);
    }

    public void createComment(Comment comment) {
        this.commentRepository.save(comment);
    }

    public CommentList getCommentByIds(List<Long> commentIds) {
        return new CommentList(commentRepository.getCommentsByIdIn(commentIds));
    }
}
