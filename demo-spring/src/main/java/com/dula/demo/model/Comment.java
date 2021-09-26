package com.dula.demo.model;

import com.dula.demo.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Comment {
    private Long id;
    private String text;
    private Post post;
    private User author;
}
