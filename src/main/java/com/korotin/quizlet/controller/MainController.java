package com.korotin.quizlet.controller;

import com.korotin.quizlet.repository.CardSetRepository;
import com.korotin.quizlet.service.CardSetService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class MainController {

    private final CardSetRepository cardSetRepository;

    private final CardSetService cardSetService;


    @GetMapping("/home")
    public String home() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());

        return "home";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }

}
