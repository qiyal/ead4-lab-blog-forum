package kz.iitu.ead4.models;

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
