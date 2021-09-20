package com.example.parking.controller;

import com.example.parking.dao.PersonnelRepository;
import com.example.parking.dao.RoleRepository;
import com.example.parking.dao.UserRepository;
import com.example.parking.helper.EmailSender;
import com.example.parking.helper.PaiementManagement;
import com.example.parking.model.Personnel;
import com.example.parking.model.Role;
import com.example.parking.model.User;
import com.example.parking.services.SecurityService;
import com.example.parking.services.UserService;
import com.example.parking.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    private final String COMPTE_CREE_MSG = "<p>Bonjour %s,</p> <p>Votre compte a été bien créé.</p><p>Merci.</p>";
    private final String COMPTE_CREE_SBJ = "Création de compte";

    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaiementManagement paiementManagement;
    @Autowired
    private PersonnelRepository personnelRepository;

    @GetMapping("/registration")
    public String registration(Model model) {
        Map<Long, String> roles = new HashMap<>();
        for (Role role : roleRepository.findAll()) {
            roles.put(role.getId(), role.getName());
        }
        model.addAttribute("roles", roles);
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute("userForm") User userForm, BindingResult bindingResult) throws MessagingException {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            Map<Long, String> roles = new HashMap<>();
            for (Role role : roleRepository.findAll()) {
                roles.put(role.getId(), role.getName());
            }
            model.addAttribute("roles", roles);
            return "registration";
        }

        userService.save(userForm);
        emailSender.sendEmailWithAttachment(String.format(COMPTE_CREE_MSG, userForm.getUsername()), "userForm.getEmail()", COMPTE_CREE_SBJ);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

    @GetMapping("/users/virement")
    public String virement(Model model) {
        Role role = roleRepository.getRoleByName("ROLE_USER");
        model.addAttribute("users", userRepository.findByRole(role));
        model.addAttribute("action", "verser");
        return "users";
    }

    @GetMapping("/users/incrementationConge")
    public String incrmentationConge(Model model) {
        Role role = roleRepository.getRoleByName("ROLE_USER");
        model.addAttribute("users", userRepository.findByRole(role));
        model.addAttribute("action", "incrementerConge");
        return "users";
    }

    @GetMapping("/users/verser/{id}")
    public String verser(@PathVariable Long id) {
        Personnel personnel = personnelRepository.getById(id);
        paiementManagement.verserSalaire(personnel);
        return "redirect:/users/virement";
    }

    @GetMapping("/users/incrementerConge/{id}")
    public String incrementerConge(@PathVariable Long id) {
        Personnel personnel = personnelRepository.getById(id);
        paiementManagement.incrementerConge(personnel);
        return "redirect:/users/incrementationConge";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}