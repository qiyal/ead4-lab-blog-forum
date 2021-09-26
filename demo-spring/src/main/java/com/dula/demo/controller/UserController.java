package com.dula.demo.controller;

import com.dula.demo.entity.Role;
import com.dula.demo.entity.User;
import com.dula.demo.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(this.userService.getUsers());
    }

    @GetMapping("/role")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok().body(this.userService.getRoles());
    }

    @PostMapping("")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(
                  ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/user")
                            .toUriString()
        );
        return ResponseEntity.created(uri).body(this.userService.saveUser(user));
    }

    @PostMapping("/role")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(
                  ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/user/role")
                            .toUriString()
        );
        return ResponseEntity.created(uri).body(this.userService.saveRole(role));
    }

    @PostMapping("/add-role")
    public ResponseEntity<Role> addRoleToUser(@RequestBody RoleToUserForm form) {
        this.userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
