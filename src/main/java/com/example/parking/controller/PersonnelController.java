package com.example.parking.controller;


import com.example.parking.dao.PersonnelDao;
import com.example.parking.model.Personnel;
import com.example.parking.model.Vehicule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value="/personnel")
public class PersonnelController {

    @Autowired
    PersonnelDao personnelDao;





    @GetMapping(value = "/all")
    public String allpersonnels(Model model) { // model pour passer des donnees
        List<Personnel> all = personnelDao.findAll();

        model.addAttribute("prs", all);
        model.addAttribute("info", "base de données");

        return "personnels";
    }

    @GetMapping(value = "/suppression/{id}")
    public String supprimer(Model model, @PathVariable Integer id) { // model pour passer des donnees
        try {
            personnelDao.deleteById(id);
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
        return allpersonnels(model);
    }

    @GetMapping(value = "/create")
    public String createPersonnel(Model model) {
        model.addAttribute("crt", new Personnel());
        return "form";
    }

    @PostMapping(value = "/new")
    public String newPersonnel(Model model, @ModelAttribute Personnel personnel) {
        personnelDao.save(personnel);

        return allpersonnels(model);
    }

    @GetMapping(value = "/{id}")
    public String afficher(Model model, @PathVariable Integer id) { // model pour passer des donnees
        Optional<Personnel> p = personnelDao.findById(id);
        if (p.isPresent()) {
            Personnel p2 = p.get();
            model.addAttribute("prs", p2);

            return "aff_per";
        } else {
            return allpersonnels(model);
        }
    }

    @GetMapping(value = "/modifier/{id}")
    public String modifierPersonnel(Model model, @PathVariable Integer id) {
        Optional<Personnel> p = personnelDao.findById(id);
        if (p.isPresent()) {
            Personnel p2 = p.get();
            model.addAttribute("mdf", p2);
            return "page_modification";
        } else {
            return "redirect:/personnel/all";
        }
    }

    @PostMapping(value = "/modifier")
    public String modifPers(Model model, @ModelAttribute Personnel personnel) {
        personnelDao.save(personnel);

        return allpersonnels(model);
    }




}
