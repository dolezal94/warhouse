package com.sda.finalproject.authmvc.controllers;

import com.sda.finalproject.authmvc.dto.LoginDto;
import com.sda.finalproject.authmvc.dto.RegisterDto;
import com.sda.finalproject.authmvc.entities.UserEntity;
import com.sda.finalproject.authmvc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String getLoginPage(Model model, @RequestParam("error") Optional<String> error) {
        var loginDto = new LoginDto();

        model.addAttribute("login", loginDto);

        if (error.isPresent()) {
            model.addAttribute("message", "Invalid password or email");
        }

        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("login") @Valid LoginDto loginDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            return "login";
        }

        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        UserEntity user = userRepository.findByEmailAndPassword(email, password);

        if (user != null) {
            return "dashboard";
        } else {
            model.addAttribute("message", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        var registerDto = new RegisterDto();

        model.addAttribute("user", registerDto);

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") RegisterDto registerDto, Model model) {

        String firstName = registerDto.getFirstName();
        String lastName = registerDto.getLastName();
        String email = registerDto.getEmail();
        String password = registerDto.getPassword();

        UserEntity user = new UserEntity(firstName, lastName, email, password);

        // Uložení uživatele do databáze pomocí odpovídajícího repository

        userRepository.save(user);

        // Nastavení zprávy o úspěšné registraci
        model.addAttribute("message", "Registration successful. Please login.");

        // Přesměrování na stránku s přihlášením
        return "redirect:/login";
    }
}
