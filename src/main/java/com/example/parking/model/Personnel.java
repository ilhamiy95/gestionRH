package com.example.parking.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.format.SignStyle;



@Entity
public class Personnel extends User{



    private String nom;
    private String prenom;
    private int age;
    private int tele;
    private String email;
    private String adress;
    private String grade;
    private int immat;



}
