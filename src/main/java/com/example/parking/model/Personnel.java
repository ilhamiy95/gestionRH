package com.example.parking.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
public class Personnel extends User {
    private String nom;
    private String prenom;
    private String tele;
    private String email;
    private String address;
    private float salaire;
    private float montant;
    private float soldeConge;
    private Diplome diplome;
    private AnnExp exp;
    private TypeContrat typeContrat;

    @OneToMany(mappedBy = "personnel")
    private List<Absence> absences;
    @OneToMany(mappedBy = "personnel")
    private List<Conge> conges;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public float getSoldeConge() {
        return soldeConge;
    }

    public void setSoldeConge(float soldeConge) {
        this.soldeConge = soldeConge;
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

    public void setExp(AnnExp exp) {
        this.exp = exp;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(TypeContrat typeContrat) {
        this.typeContrat = typeContrat;
    }

    public List<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(List<Absence> absences) {
        this.absences = absences;
    }

    public List<Conge> getConges() {
        return conges;
    }

    public void setConges(List<Conge> conges) {
        this.conges = conges;
    }
}