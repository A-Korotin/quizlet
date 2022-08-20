package com.korotin.quizlet.controller;

import com.korotin.quizlet.domain.security.User;
import com.korotin.quizlet.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user,
                          BindingResult result,
                          Model model) {

        if (result.hasErrors())
            return "registration";

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("message", "Passwords don't match!");
            return "registration";
        }



        boolean userAdded = userService.saveUser(user);

        if(!userAdded) {
            model.addAttribute("message", "User already exists!");
            return "registration";
        }


        return "redirect:/login";
    }
}
