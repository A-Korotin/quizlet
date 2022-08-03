package com.korotin.quizlet.controller;

import com.korotin.quizlet.domain.Role;
import com.korotin.quizlet.domain.User;
import com.korotin.quizlet.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Set;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if(userFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Set.of(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}
