package com.dula.demo.service;

import com.dula.demo.entity.Role;
import com.dula.demo.entity.User;
import com.dula.demo.repository.RoleRepository;
import com.dula.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

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

    public User getUser(String username) {
        return this.userRepo.findByUsername(username);
    }

    public List<User> getUsers() {
        return this.userRepo.findAll();
    }

    public List<Role> getRoles() {
        return this.roleRepo.findAll();
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
