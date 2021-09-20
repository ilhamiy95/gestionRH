package com.example.parking.controller;

import com.example.parking.dao.CandidatDao;
import com.example.parking.dao.RoleRepository;
import com.example.parking.dao.UserRepository;
import com.example.parking.helper.EmailSender;
import com.example.parking.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/candidat")
public class CandidatureController {
    private final String CANDIDATURE_MSG = "<p>Bonjour %s %s,</p> <p>Votre candidature a été bien reçu.</p>" +
            "<p>Vous recevrez un email de résultat dans les prochain 7 jours</p>" +
            "<p>Merci.</p>";
    private final String CANDIDATURE_SBJ = "Candidature à SQLI";
    private final String CANDIDATURE_ACCEPTEE_MSG = "<p>Bonjour %s %s,</p> <p>Votre candidature a été bien acceptée.</p><p>Votre compte est : %s/%S</p><p>Merci.</p>";
    private final String CANDIDATURE_REFUSEE_MSG = "<p>Bonjour %s %s,</p> <p>Votre candidature a été malheureusement refusée.</p><p>Merci.</p>";

    @Autowired
    CandidatDao candidatDao;
    @Autowired
    EmailSender emailSender;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "/allca")
    public String allcandidats(Model model) {
        List<Candidat> all = candidatDao.findAll();
        model.addAttribute("can", all);
        return "listeCandidat";
    }

    @GetMapping(value = "/create")
    public String createCandidat(Model model) {
        model.addAttribute("can", new Candidat());
        model.addAttribute("AnnExp", AnnExp.getValues());
        model.addAttribute("StatusCandidat", StatusCandidat.getValues());
        return "candidature";
    }

    @PostMapping(value = "/new")
    public String newCandidat(@Valid @ModelAttribute Candidat candidat, BindingResult bindingResult, Model model) throws MessagingException {
        if (bindingResult.hasErrors()) {
            return "redirect:/candidat/create";
        }
        candidat.setStatus(StatusCandidat.ENCOURS);
        candidatDao.save(candidat);
        emailSender.sendEmailWithAttachment(String.format(CANDIDATURE_MSG, candidat.getPrenom(), candidat.getNom()), candidat.getEmail(), CANDIDATURE_SBJ);
        model.addAttribute("success", "Bonjour " + candidat.getPrenom() + " " + candidat.getNom() + ", Nous avons le plaisir de vous informer que votre candidature a retenu toute notre attention, Et en cours de traitement par le jury");
        return createCandidat(model);
    }

    @GetMapping(value = "/accepter/{id}")
    public String accepter(@PathVariable Integer id) throws MessagingException {
        Candidat candidat = candidatDao.getById(id);
        if (candidat.getStatus() != StatusCandidat.ACCEPTE) {
            candidat.setStatus(StatusCandidat.ACCEPTE);
            candidatDao.save(candidat);
            emailSender.sendEmailWithAttachment(String.format(CANDIDATURE_ACCEPTEE_MSG, candidat.getPrenom(), candidat.getNom(),
                    candidat.getNom() + "_" + candidat.getPrenom(),
                    "1234567A"), candidat.getEmail(), CANDIDATURE_SBJ);
            createUserFromCondidat(candidat);
        }
        return "redirect:/candidat/allca";
    }

    @GetMapping(value = "/refuser/{id}")
    public String refuser(@PathVariable Integer id) throws MessagingException {
        Candidat candidat = candidatDao.getById(id);
        if (candidat.getStatus() != StatusCandidat.REFUSE) {
            candidat.setStatus(StatusCandidat.REFUSE);
            candidatDao.save(candidat);
            emailSender.sendEmailWithAttachment(String.format(CANDIDATURE_REFUSEE_MSG,
                    candidat.getPrenom(),
                    candidat.getNom()),
                    candidat.getEmail(), CANDIDATURE_SBJ);
        }
        return "redirect:/candidat/allca";
    }

    private void createUserFromCondidat(Candidat candidat) {
        Personnel personnel = new Personnel();
        personnel.setNom(candidat.getNom().trim());
        personnel.setPrenom(candidat.getPrenom().trim());
        personnel.setUsername(candidat.getNom().trim() + "_" + candidat.getPrenom().trim());
        personnel.setRole(roleRepository.getRoleByName("ROLE_USER"));
        personnel.setAddress(candidat.getAdress());
        personnel.setTele(candidat.getTele());
        personnel.setSalaire(9500F);
        personnel.setEmail(candidat.getEmail());
        personnel.setPassword(bCryptPasswordEncoder.encode("1234567A"));
        personnel.setDiplome(candidat.getDiplome());
        personnel.setExp(candidat.getExp());
        personnel.setTypeContrat(TypeContrat.CDI);
        userRepository.save(personnel);
    }
}
