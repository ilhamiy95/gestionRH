package com.example.parking.dao;

import com.example.parking.model.Conge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CongeDao extends JpaRepository<Conge, Integer> {
}
