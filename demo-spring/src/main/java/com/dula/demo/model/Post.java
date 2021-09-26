package com.dula.demo.model;

import com.dula.demo.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Post {
    private Long id;
    private String title;
    private String text;
    private User author;
    private List<Comment> comments;
}
