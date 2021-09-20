package com.example.parking.controller;


import com.example.parking.dao.CongeDao;
import com.example.parking.dao.PersonnelRepository;
import com.example.parking.dao.RoleRepository;
import com.example.parking.helper.EmailSender;
import com.example.parking.helper.PaiementManagement;
import com.example.parking.model.Conge;
import com.example.parking.model.StatusConge;
import com.example.parking.model.TypeConge;
import com.example.parking.validator.CongeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/conge")
public class CongeController {
    private final String CONGE_REFUSE_MSG = "<p>Bonjour %s,</p> <p>Votre congé du %s à %s a été refusé.</p><p>Merci.</p>";
    private final String CONGE_REFUSE_SBJ = "Congé refusé";
    private final String CONGE_CREE_MSG = "<p>Bonjour %s,</p> <p>Votre congé du %s à %s a été bien créé.</p><p>Merci.</p>";
    private final String CONGE_CREE_SBJ = "Congé créé";
    private final String CONGE_ENCOURS_MSG = "<p>Bonjour %s,</p> <p>Votre congé du %s à %s a été bien modifié.</p> <p>Il est en cours de validation.</p><p>Merci.</p>";
    private final String CONGE_ENCOURS_SBJ = "Congé en cours de validation";
    private final String CONGE_ACCEPTE_MSG = "<p>Bonjour %s,</p> <p>Votre congé du %s à %s a été accepté.</p> <p>Merci.</p>";
    private final String CONGE_ACCEPTE_SBJ = "Congé validé";
    private final String pattern = "dd-MM-yyyy";
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    @Autowired
    CongeDao congeDao;
    @Autowired
    private PersonnelRepository personnelRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private CongeValidator congeValidator;
    @Autowired
    private PaiementManagement paiementManagement;

    @GetMapping(value = "/ajouter")
    public String ajouterConge(Model model, Principal principal) {
        model.addAttribute("congee", new Conge());
        model.addAttribute("soldeConge", personnelRepository.findByUsername(principal.getName()).getSoldeConge());
        model.addAttribute("typesConge", TypeConge.getValues());
        return "ajouter_conge";
    }

    @PostMapping(value = "/ajouter")
    public String saveConge(Model model, @ModelAttribute("congee") Conge conge, BindingResult bindingResult, Principal principal) throws MessagingException {
        conge.setPersonnel(personnelRepository.findByUsername(principal.getName()));
        congeValidator.validate(conge, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("typesConge", TypeConge.getValues());
            model.addAttribute("soldeConge", personnelRepository.findByUsername(principal.getName()).getSoldeConge());
            return "ajouter_conge";
        }
        conge.setStatus(StatusConge.EN_COURS);
        congeDao.save(conge);
        emailSender.sendEmailWithAttachment(String.format(CONGE_CREE_MSG, conge.getPersonnel().getUsername(),
                simpleDateFormat.format(conge.getDateDebut()),
                simpleDateFormat.format(conge.getDateFin())), conge.getPersonnel().getEmail(), CONGE_CREE_SBJ);
        return "redirect:/conge/allc";
    }

    @GetMapping(value = "/modifier/{id}")
    public String ajouterConge(Model model, @PathVariable Integer id, Principal principal) {
        Conge c1 = congeDao.getById(id);
        model.addAttribute("congee", c1);
        model.addAttribute("soldeConge", personnelRepository.findByUsername(principal.getName()).getSoldeConge());
        return "modifier_conge";
    }

    @PostMapping(value = "/modifier/{id}")
    public String modifc(Model model, @PathVariable Integer id, @ModelAttribute("congee") Conge conge, BindingResult bindingResult, Principal principal) throws MessagingException {
        conge.setPersonnel(personnelRepository.findByUsername(principal.getName()));
        congeValidator.validate(conge, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("typesConge", TypeConge.getValues());
            return "mofidier_conge";
        }
        conge.setId(id);
        conge.setStatus(StatusConge.EN_COURS);
        congeDao.save(conge);
        emailSender.sendEmailWithAttachment(String.format(CONGE_ENCOURS_MSG, conge.getPersonnel().getUsername(),
                simpleDateFormat.format(conge.getDateDebut()),
                simpleDateFormat.format(conge.getDateFin())), conge.getPersonnel().getEmail(), CONGE_ENCOURS_SBJ);
        return "redirect:/conge/allc";
    }

    @GetMapping(value = "/allc")
    public String allconge(Authentication auth, Model model) {
        List<Conge> all = congeDao.findAll();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            all = all.stream().filter(c -> c.getPersonnel().getUsername().equals(auth.getName())).collect(Collectors.toList());
        }
        model.addAttribute("conges", all);
        return "conge-list";
    }

    @GetMapping(value = "/accepter/{id}")
    public String accepter(@PathVariable Integer id) throws MessagingException {
        Conge conge = congeDao.getById(id);
        if (conge.getStatus() != StatusConge.ACCEPTE) {
            conge.setStatus(StatusConge.ACCEPTE);
            congeDao.save(conge);
            emailSender.sendEmailWithAttachment(String.format(CONGE_ACCEPTE_MSG, conge.getPersonnel().getUsername(),
                    simpleDateFormat.format(conge.getDateDebut()),
                    simpleDateFormat.format(conge.getDateFin())), conge.getPersonnel().getEmail(), CONGE_ACCEPTE_SBJ);
            if (TypeConge.SANS_SOLDE.equals(conge.getType())) {
                paiementManagement.soustractSalaryByConge(conge);
            } else if (TypeConge.PAYE.equals(conge.getType())) {
                paiementManagement.soustractSoldeConge(conge);
            }
        }
        return "redirect:/conge/allc";
    }

    @GetMapping(value = "/refuser/{id}")
    public String allconge(@PathVariable Integer id) throws MessagingException {
        Conge conge = congeDao.getById(id);
        if (conge.getStatus() != StatusConge.REFUSE) {
            conge.setStatus(StatusConge.REFUSE);
            congeDao.save(conge);
            emailSender.sendEmailWithAttachment(String.format(CONGE_REFUSE_MSG, conge.getPersonnel().getUsername(),
                    simpleDateFormat.format(conge.getDateDebut()),
                    simpleDateFormat.format(conge.getDateFin())), conge.getPersonnel().getEmail(), CONGE_REFUSE_SBJ);
        }
        return "redirect:/conge/allc";
    }
}
