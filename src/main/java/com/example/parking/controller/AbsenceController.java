package com.example.parking.controller;

import com.example.parking.dao.AbsenceDao;
import com.example.parking.dao.PersonnelRepository;
import com.example.parking.helper.DateDiff;
import com.example.parking.model.Absence;
import com.example.parking.model.Personnel;
import com.example.parking.validator.AbsenceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/absence")
public class AbsenceController {
    @Autowired
    AbsenceDao absenceDao;
    @Autowired
    PersonnelRepository personnelRepository;
    @Autowired
    AbsenceValidator absenceValidator;

    @GetMapping(value = "/ajouter")
    public String ajouterAbsence(Model model) {
        model.addAttribute("absence", new Absence());
        List<Personnel> personnels = personnelRepository.findAll();
        Map<Long, String> u = new HashMap<>();
        for (Personnel personnel : personnels) {
            u.put(personnel.getId(), personnel.getUsername());
        }
        model.addAttribute("users", u);
        return "ajouter_absence";
    }

    @PostMapping(value = "/new")
    public String newAbsence(Model model, @ModelAttribute("absence") Absence absence, BindingResult bindingResult) {
        absenceValidator.validate(absence, bindingResult);
        if (bindingResult.hasErrors()) {
            List<Personnel> personnels = personnelRepository.findAll();
            Map<Long, String> u = new HashMap<>();
            for (Personnel personnel : personnels) {
                u.put(personnel.getId(), personnel.getUsername());
            }
            model.addAttribute("users", u);
            return "ajouter_absence";
        }
        absence.setNombreJour(DateDiff.numberOfDaysExcludingWeekend(absence.getDateDebut(), absence.getDateFin()));
        absenceDao.save(absence);
        return "redirect:/absence/allab";
    }

    @GetMapping(value = "/allab")
    public String allabsence(Model model, Authentication auth) {
        List<Absence> all = absenceDao.findAll();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            all = all.stream().filter(c -> c.getPersonnel().getUsername().equals(auth.getName())).collect(Collectors.toList());
        }
        model.addAttribute("ab", all);
        return "liste_absence";
    }

    @GetMapping(value = "/suppression/{id}")
    public String supprimer(Model model, @PathVariable Integer id) {
        absenceDao.deleteById(id);
        return "redirect:/absence/allab";
    }

    @GetMapping(value = "/modifier/{id}")
    public String modifierAbs(Model model, @PathVariable Integer id) {
        Absence a = absenceDao.getById(id);
        model.addAttribute("absence", a);
        List<Personnel> personnels = personnelRepository.findAll();
        Map<Long, String> u = new HashMap<>();
        for (Personnel personnel : personnels) {
            u.put(personnel.getId(), personnel.getUsername());
        }
        model.addAttribute("users", u);
        return "modifier_abs";
    }

    @PostMapping(value = "/modifier/{id}")
    public String modifier(Model model, @PathVariable Integer id, @ModelAttribute("absence") Absence absence, BindingResult bindingResult) {
        absenceValidator.validate(absence, bindingResult);
        if (bindingResult.hasErrors()) {
            List<Personnel> personnels = personnelRepository.findAll();
            Map<Long, String> u = new HashMap<>();
            for (Personnel personnel : personnels) {
                u.put(personnel.getId(), personnel.getUsername());
            }
            model.addAttribute("users", u);
            return "modifier_abs";
        }
        absence.setId(id);
        absence.setNombreJour(DateDiff.numberOfDaysExcludingWeekend(absence.getDateDebut(), absence.getDateFin()));
        absenceDao.save(absence);
        return "redirect:/absence/allab";
    }
}
