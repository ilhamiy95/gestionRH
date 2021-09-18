package com.example.parking.controller;

import com.example.parking.dao.PersonnelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/admin")
public class MenuAdminController {



        @Autowired
        PersonnelDao personnelDao;


        @GetMapping(value = "/menuadmin")
        public String espace(Model model) { // model pour passer des donnees

            return "menu-admin";
        }
}
