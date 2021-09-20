package com.example.parking.controller;

import com.example.parking.dao.PersonnelRepository;
import com.example.parking.dao.RoleRepository;
import com.example.parking.helper.EmailSender;
import com.example.parking.model.*;
import com.example.parking.services.SecurityService;
import com.example.parking.services.UserService;
import com.example.parking.validator.PersonnelValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/personnel")
public class PersonnelController {
    private static final String PERSONNELS = "redirect:/personnel/all";
    private final String COMPTE_CREE_MSG = "<p>Bonjour %s,</p> <p>Votre compte a été bien créé.</p><p>Merci.</p>";
    private final String COMPTE_CREE_SBJ = "Création de compte";

    @Autowired
    PersonnelRepository personnelRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EmailSender emailSender;
    @Autowired
    SecurityService securityService;
    @Autowired
    UserService userService;
    @Autowired
    PersonnelValidator personnelValidator;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "/all")
    public String allpersonnels(Model model) {
        List<Personnel> personnels = personnelRepository.findAll();
        model.addAttribute("personnels", personnels);
        return "personnels";
    }

    @GetMapping(value = "/ajouter")
    public String create(Model model) {
        model.addAttribute("personnel", new Personnel());
        model.addAttribute("diplomes", Diplome.getValues());
        model.addAttribute("exps", AnnExp.getValues());
        model.addAttribute("typeContrats", TypeContrat.getValues());
        Map<Long, String> roles = new HashMap<>();
        for (Role role : roleRepository.findAll()) {
            roles.put(role.getId(), role.getName());
        }
        model.addAttribute("roles", roles);
        return "ajouter_personnel";
    }

    @PostMapping(value = "/ajouter")
    public String newPersonnel(Model model, @ModelAttribute("personnel") Personnel personnel, BindingResult bindingResult) throws MessagingException {
        personnelValidator.validate(personnel, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("diplomes", Diplome.getValues());
            model.addAttribute("exps", AnnExp.getValues());
            model.addAttribute("typeContrats", TypeContrat.getValues());
            Map<Long, String> roles = new HashMap<>();
            for (Role role : roleRepository.findAll()) {
                roles.put(role.getId(), role.getName());
            }
            model.addAttribute("roles", roles);
            return "ajouter_personnel";
        }
        personnel.setPassword(bCryptPasswordEncoder.encode(personnel.getPassword()));
        personnelRepository.save(personnel);
//        securityService.autoLogin(personnel.getUsername(), personnel.getPasswordConfirm()); // a éviter prq je suis admin
        emailSender.sendEmailWithAttachment(String.format(COMPTE_CREE_MSG, personnel.getUsername()), "userForm.getEmail()", COMPTE_CREE_SBJ); // username/password
        return PERSONNELS;
    }

    @GetMapping(value = "/suppression/{id}")
    public String supprimer(@PathVariable Long id) {
        personnelRepository.deleteById(id);
        return PERSONNELS;
    }

    @GetMapping(value = "/afficher")
    public String profile(Model model, Principal principal) {
        Personnel personnel = personnelRepository.findByUsername(principal.getName());
        model.addAttribute("personnel", personnel);
        return "afficher_personnel";
    }

    @GetMapping(value = "/afficher/{id}")
    public String afficher(Model model, @PathVariable Long id) {
        Personnel personnel = personnelRepository.getById(id);
        model.addAttribute("personnel", personnel);
        return "afficher_personnel";
    }

    @GetMapping(value = "/modifier/{id}")
    public String modifierPersonnel(Model model, @PathVariable Long id) {
        Personnel personnel = personnelRepository.getById(id);
        model.addAttribute("personnel", personnel);
        return "modifier_personnel";
    }

    @PostMapping(value = "/modifier/{id}")
    public String modifPers(@PathVariable Long id, @ModelAttribute Personnel personnel) {
        Personnel p = personnelRepository.getById(id);
        p.setUsername(personnel.getUsername());
        p.setNom(personnel.getNom());
        p.setPrenom(personnel.getPrenom());
        p.setEmail(personnel.getEmail());
        p.setTele(personnel.getTele());
        p.setSalaire(personnel.getSalaire());
        p.setAddress(personnel.getAddress());
        p.setDiplome(personnel.getDiplome());
        p.setExp(personnel.getExp());
        p.setTypeContrat(personnel.getTypeContrat());
        personnelRepository.save(p);
        return PERSONNELS;
    }
}
