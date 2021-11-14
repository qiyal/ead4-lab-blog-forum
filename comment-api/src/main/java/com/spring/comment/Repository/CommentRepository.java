package com.spring.comment.repository;
import com.spring.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByUserId(Long userId);
    List<Comment> getCommentsByIdIn(List<Long> ids);
    Comment getById(Long id);
}
