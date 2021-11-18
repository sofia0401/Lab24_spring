package com.example.demo.controllers;

import com.example.demo.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class UserController {
    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/sign")
    public String index() {
        return "userController/signup";
    }

    @GetMapping("/start")
    public String index2(Authentication authentication, Model model) {
        model.addAttribute("user_login", "Добро пожаловать, "+authentication.getName());
        return "userController/main";
    }
    @PostMapping("/signuperror")
    public String SignUp(@RequestParam String login, String password, String password2, Model model) {
        if(!password.equals(password2)){
            model.addAttribute("Status", "password!=password2");
            return "userController/signup";
        }
        else {
            if (userDAO.loadUserByUsername(login) != null) {
                model.addAttribute("Status", "user_exists");
                return "userController/signup";
            } else {
                userDAO.create(login, password);
                return "redirect:/start";
            }
        }
    }
}


