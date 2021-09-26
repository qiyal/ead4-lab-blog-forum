package com.dula.demo.model;

import com.dula.demo.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class Forum {
    private Long id;
    private String title;
    private User owner;
    private Collection<User> members;
}
