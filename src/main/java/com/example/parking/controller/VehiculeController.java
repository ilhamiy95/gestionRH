package com.example.parking.controller;


import com.example.parking.dao.VehiculeDao;
import com.example.parking.model.Vehicule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/vehicule")
public class VehiculeController {

    @Autowired
    VehiculeDao vehiculeDao;

    @GetMapping
    public String saveVehicules(Model model) { // model pour passer des donnees
        Vehicule v1 = new Vehicule(5,4010, "youssef");
        Vehicule v2 = new Vehicule(6,2000, "ilham");
        Vehicule v3 = new Vehicule(7,3000, "salma");


        vehiculeDao.save(v1);
        vehiculeDao.save(v2);
        vehiculeDao.save(v3);


        List<Vehicule> voitures = new ArrayList<>();

        voitures.add(v1);
        voitures.add(v2);
        voitures.add(v3);

        model.addAttribute("tomobilat", voitures);
        model.addAttribute("info", "code java");

        return "vehicules";
    }

    @GetMapping(value = "/all")
    public String allVehicules(Model model) { // model pour passer des donnees
        List<Vehicule> vehicules = vehiculeDao.findAll();

        model.addAttribute("tomobilat", vehicules);
        model.addAttribute("info", "base de données");

        return "vehicules";
    }

    @GetMapping(value = "/{id}")
    public String supprimer(Model model, @PathVariable Integer id) { // model pour passer des donnees
        vehiculeDao.deleteById(id);
        List<Vehicule> vehicules = vehiculeDao.findAll();

        model.addAttribute("tomobilat", vehicules);
        model.addAttribute("info", "base de données apres suppression");

        return "vehicules";
    }




}


