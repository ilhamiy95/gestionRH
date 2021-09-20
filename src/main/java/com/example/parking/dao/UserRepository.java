package com.example.parking.dao;

import com.example.parking.model.Role;
import com.example.parking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findByRole(Role role);
}