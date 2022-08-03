package com.korotin.quizlet.controller;

import com.korotin.quizlet.domain.CardSet;
import com.korotin.quizlet.repository.CardRepository;
import com.korotin.quizlet.repository.CardSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MainController {

    private CardRepository cardRepository;

    private CardSetRepository cardSetRepository;

    @Autowired
    public MainController(CardRepository cardRepository, CardSetRepository cardSetRepository) {
        this.cardRepository = cardRepository;
        this.cardSetRepository = cardSetRepository;
    }

    @GetMapping("/")
    public String home() {
        return "startPage";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("set", new CardSet());
        return "createSet";
    }

    @PostMapping("/create")
    public String addSet(@ModelAttribute CardSet cardSet) {
        System.out.println(cardSet);
        return "redirect:/";
    }

}
