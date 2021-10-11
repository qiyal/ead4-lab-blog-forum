package com.spring.comment.Repository;
import com.spring.comment.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByUserId(Long userId);
    List<Comment> getCommentsByIdIn(List<Long> ids);
}
