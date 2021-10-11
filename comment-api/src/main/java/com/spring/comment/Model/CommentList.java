package com.spring.comment.Model;

import com.spring.comment.Entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentList {
    private List<Comment> comments;
}
