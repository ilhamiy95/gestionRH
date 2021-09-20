package com.example.parking.dao;

import com.example.parking.model.Personnel;
import com.example.parking.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {
    Personnel findByUsername(String username);

    List<Personnel> findByRole(Role role);
}