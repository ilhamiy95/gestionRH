package com.example.parking.dao;

import com.example.parking.model.Absence;
import com.example.parking.model.Candidat;
import com.example.parking.model.Conge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceDao extends JpaRepository<Absence, Integer>   {
}
