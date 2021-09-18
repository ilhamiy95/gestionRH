package com.example.parking.dao;

import com.example.parking.model.Conge;
import com.example.parking.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CongeDao extends JpaRepository<Conge, Integer> {
}
