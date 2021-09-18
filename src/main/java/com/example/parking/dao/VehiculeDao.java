package com.example.parking.dao;

import com.example.parking.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeDao extends JpaRepository<Vehicule, Integer> {
}
