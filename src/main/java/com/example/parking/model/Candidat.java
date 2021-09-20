package com.example.parking.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @Size(min = 2, max = 5, message = "le nom doit etre entre 2 et 5 caracteres")
    private String nom;
    private String prenom;
    private Integer age;
    private String tele;
    private String email;
    private String adress;
    private Diplome diplome;
    private AnnExp exp;
    private StatusCandidat status;

    public int getId() {
        return id;
    }

    public Candidat setId(int id) {
        this.id = id;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public Candidat setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getPrenom() {
        return prenom;
    }

    public Candidat setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Candidat setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getTele() {
        return tele;
    }

    public Candidat setTele(String tele) {
        this.tele = tele;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Candidat setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAdress() {
        return adress;
    }

    public Candidat setAdress(String adress) {
        this.adress = adress;
        return this;
    }

    public Diplome getDiplome() {
        return diplome;
    }

    public void setDiplome(Diplome diplome) {
        this.diplome = diplome;
    }

    public AnnExp getExp() {
        return exp;
    }

    public Candidat setExp(AnnExp exp) {
        this.exp = exp;
        return this;
    }

    public StatusCandidat getStatus() {
        return status;
    }

    public Candidat setStatus(StatusCandidat status) {
        this.status = status;
        return this;
    }
}
