package com.example.parking.dao;

import com.example.parking.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByName(String name);
}