package com.dula.demo.service;

import com.dula.demo.model.Forum;
import com.dula.demo.model.Post;
import com.dula.demo.entity.Role;
import com.dula.demo.entity.User;
import com.dula.demo.repository.RoleRepository;
import com.dula.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final RestTemplate restTemplate;

    public User saveUser(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepo.save(user);
    }

    public Role saveRole(Role role) {
        return this.roleRepo.save(role);
    }

    public void addRoleToUser(String username, String roleName) {
        User user = this.userRepo.findByUsername(username);
        Role role = this.roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    public User getUserById(Long userId) {
        return this.userRepo.getById(userId);
    }

    public User getUserByUsername(String username) {
        return this.userRepo.findByUsername(username);
    }

    public List<User> getUsers() {
        return this.userRepo.findAll();
    }

//    public List<User> getUsers(Map<String, String> params) {
//        return this.userRepo.findAll(Example);
//    }

    public List<Role> getRoles() {
        return this.roleRepo.findAll();
    }

    public List<Forum> getUserForums(Long userId) {
        ResponseEntity<Forum[]> response = this.restTemplate.getForEntity(
                  "http://localhost:8081/forum/user/userId=" + userId,
                  Forum[].class
        );
        Forum[] forums = response.getBody();

        return Arrays.asList(forums != null ? forums : new Forum[0]);
    }

    public List<Post> getUserPosts(Long userId) {
        ResponseEntity<Post[]> response = this.restTemplate.getForEntity(
                  "http://localhost:8081/post/user/userId" + userId,
                  Post[].class
        );
        Post[] posts = response.getBody();

        return Arrays.asList(posts != null ? posts : new Post[0]);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database!");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
