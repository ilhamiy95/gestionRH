package com.example.parking.helper;

import com.example.parking.dao.UserRepository;
import com.example.parking.model.Conge;
import com.example.parking.model.Personnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaiementManagement {

    @Autowired
    UserRepository userRepository;

    public void soustractSalaryByConge(Conge conge) {
        Personnel personnel = conge.getPersonnel();
        int numberOfDaysExcludingWeekend = DateDiff.numberOfDaysExcludingWeekend(conge.getDateDebut(), conge.getDateFin());
        personnel.setMontant(personnel.getMontant() - (personnel.getSalaire() / 30) * numberOfDaysExcludingWeekend);
        userRepository.save(personnel);
    }

    public void soustractSoldeConge(Conge conge) {
        Personnel personnel = conge.getPersonnel();
        int numberOfDaysExcludingWeekend = DateDiff.numberOfDaysExcludingWeekend(conge.getDateDebut(), conge.getDateFin());
        personnel.setSoldeConge(personnel.getSoldeConge() - numberOfDaysExcludingWeekend);
        userRepository.save(personnel);
    }

    public void verserSalaire(Personnel personnel) {
        personnel.setMontant(personnel.getMontant() + personnel.getSalaire());
        userRepository.save(personnel);
    }

    public void incrementerConge(Personnel personnel) {
        personnel.setSoldeConge(personnel.getSoldeConge() + 1.5f);
        userRepository.save(personnel);
    }
}
