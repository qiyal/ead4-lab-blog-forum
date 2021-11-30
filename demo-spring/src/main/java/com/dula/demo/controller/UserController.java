package com.dula.demo.controller;

import com.dula.demo.model.Forum;
import com.dula.demo.entity.Role;
import com.dula.demo.entity.User;
import com.dula.demo.model.Post;
import com.dula.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
//@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

//    GET
    @GetMapping("")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(this.userService.getUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok().body(this.userService.getUserById(userId));
    }

    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok().body(this.userService.getUserByUsername(username));
    }

    @GetMapping("/role")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok().body(this.userService.getRoles());
    }

    @GetMapping("/forums/{userId}")
    public ResponseEntity<List<Forum>> getUserForums(@PathVariable Long userId) {
        return ResponseEntity.ok().body(this.userService.getUserForums(userId));
    }

    @GetMapping("/posts/{userId}")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable Long userId) {
        return ResponseEntity.ok().body(this.userService.getUserPosts(userId));
    }

//    POST
    @PostMapping("/create")
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
    public ResponseEntity<Role> addRoleToUser(@RequestBody Map<String, String> reqBody) {
        this.userService.addRoleToUser(reqBody.get("username"), reqBody.get("password"));
        return ResponseEntity.ok().build();
    }
}

