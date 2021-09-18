package com.example.parking.services;


import com.example.parking.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}