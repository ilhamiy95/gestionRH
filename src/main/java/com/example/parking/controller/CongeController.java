package com.example.parking.controller;


import com.example.parking.dao.CongeDao;
import com.example.parking.dao.PersonnelDao;
import com.example.parking.dao.RoleRepository;
import com.example.parking.dao.UserRepository;
import com.example.parking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping(value="/conge")
public class CongeController {
    @Autowired
    CongeDao congeDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @GetMapping(value = "/ajouter")
    public String ajouterConge(Model model) {
        Conge c1 = new Conge();

        model.addAttribute("congee", c1);
        model.addAttribute("typesConge" , TypeConge.getValues());
        model.addAttribute("info", "code java");
        List<User> users = userRepository.findAll();
        Map<Long, String> u = new HashMap<>();
        for (User user : users) {
            u.put(user.getId(), user.getUsername());
        }
        model.addAttribute("users", u);
        return "conge";
    }

    @GetMapping(value = "/modifier/{id}")
    public String ajouterConge(Model model, @PathVariable Integer id) {
        Conge c1 = congeDao.getById(id);

        model.addAttribute("congee", c1);
        model.addAttribute("info", "3amer");

        List<User> users = userRepository.findAll();
        Map<Long, String> u = new HashMap<>();
        for (User user : users) {
            u.put(user.getId(), user.getUsername());
        }
        model.addAttribute("users", u);

        return "conge";
    }

    @PostMapping(value = "/sauvegarder")
    public String saveConge(Model model, @ModelAttribute Conge conge) {
        conge.setStatus(StatusConge.EN_COURS);
        congeDao.save(conge);
        model.addAttribute("info", "code java");
        return allconge(model);
    }

    @GetMapping(value = "/allc")
    public String allconge(Model model) { // model pour passer des donnees
        List<Conge> all = congeDao.findAll();

        model.addAttribute("conges", all);
        model.addAttribute("info", "base de données");

        return "conge-list";
    }

    @GetMapping(value = "/accepter/{id}")
    public String accepter(@PathVariable Integer id) { // model pour passer des donnees
        Conge conge = congeDao.getById(id); 
        conge.setStatus(StatusConge.ACCEPTE);
        congeDao.save(conge);
        return "redirect:/conge/allc";
    }

    @GetMapping(value = "/refuser/{id}")
    public String allconge(@PathVariable Integer id) { // model pour passer des donnees
        Conge conge = congeDao.getById(id);
        conge.setStatus(StatusConge.REFUSE);
        congeDao.save(conge);
        return "redirect:/conge/allc";
    }

    @GetMapping(value = "/byuser")
    public String congeByUser(Model model) { // model pour passer des donnees
        List<Conge> all = congeDao.findAll();

        model.addAttribute("cn", all);
        model.addAttribute("info", "base de données");

        return "conge-list";
    }

    @GetMapping(value = "/test")
    public String test(Model model) {

        //List<Conge> conges=congeDao.findAll();

        User  user =new User();
        user.setAge(26);
        user.setImmat(26);
        user.setEchelle("chef");

        userRepository.save(user);

        Conge conge1=new Conge();
        Conge conge2=new Conge();

        conge1.setDateDebut(new Date());
        conge1.setDateFin(new Date());

        conge2.setDateDebut(new Date());
        conge2.setDateFin(new Date());

        conge1.setUser(user);
        conge2.setUser(user);

        congeDao.save(conge1);
        congeDao.save(conge2);



        return ajouterConge(model);
    }

    @GetMapping(value = "/test2")
    public String test2(Model model) {

        Role role=new Role();
        role.setName("controlleur");

        roleRepository.save(role);

        User  user =new User();
        user.setAge(26);
        user.setImmat(26);
        user.setEchelle("chef");
        user.setRole(role);  //Role role

        userRepository.save(user);

        return ajouterConge(model);
    }




}
