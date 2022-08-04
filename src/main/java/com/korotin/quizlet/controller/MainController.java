package com.korotin.quizlet.controller;

import com.korotin.quizlet.domain.CardSet;
import com.korotin.quizlet.domain.User;
import com.korotin.quizlet.repository.CardRepository;
import com.korotin.quizlet.repository.CardSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
public class MainController {

    private CardRepository cardRepository;

    private CardSetRepository cardSetRepository;

    @Autowired
    public MainController(CardRepository cardRepository,
                          CardSetRepository cardSetRepository) {
        this.cardRepository = cardRepository;
        this.cardSetRepository = cardSetRepository;
    }

    @GetMapping("/")
    public String home() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());

        return "startPage";
    }

    @GetMapping("/create")
    public String create(Model model,
                         @RequestParam(defaultValue = "3") int numberOfCards) {
        model.addAttribute("set", new CardSet());
        model.addAttribute("nCards", numberOfCards);
        return "createSet";
    }

    @PostMapping("/create")
    public String addSet(@ModelAttribute CardSet cardSet) {
        System.out.println(cardSetRepository.save(cardSet));
        return "redirect:/";
    }

    @GetMapping("/set/{id}")
    public String viewSet(@PathVariable UUID id) {
        System.out.println(cardSetRepository.findAllById(List.of(id)));
        return "redirect:/";
    }
}
