package com.example.parking.controller;

import com.example.parking.dao.CongeDao;
import com.example.parking.dao.PersonnelDao;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/employer")
public class EmployerController {

    @Autowired
    PersonnelDao personnelDao;


    @GetMapping(value = "/menu")
    public String espace(Model model) { // model pour passer des donnees

        return "per_per";
    }



}
