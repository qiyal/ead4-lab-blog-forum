package com.dula.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    Long id;
    String username;
    String name;
    String surname;
    String password;
}
