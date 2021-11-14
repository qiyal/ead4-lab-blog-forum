package com.dula.demo.repository;

import com.dula.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String name);
    User getById(Long id);
}
