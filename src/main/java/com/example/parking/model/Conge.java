package com.example.parking.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Conge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;

    private TypeConge type;
    private StatusConge status;

    @ManyToOne
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public TypeConge getType() {
        return type;
    }

    public void setType(TypeConge type) {
        this.type = type;
    }

    public StatusConge getStatus() {
        return status;
    }

    public void setStatus(StatusConge status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
