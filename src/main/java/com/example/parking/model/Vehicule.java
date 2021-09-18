package com.example.parking.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

// lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
// lombok
//BD
@Entity
//BD
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "immat")
    private int matricule;
    private String conducteur;

}
