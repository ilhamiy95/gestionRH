package com.example.parking.controller;

import com.example.parking.dao.RoleRepository;
import com.example.parking.model.Role;
import com.example.parking.model.User;
import com.example.parking.services.SecurityService;
import com.example.parking.services.UserService;
import com.example.parking.validator.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private RoleRepository roleRepository;

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
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

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
}