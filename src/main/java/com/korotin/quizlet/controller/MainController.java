package com.korotin.quizlet.controller;

import com.korotin.quizlet.domain.CardSet;
import com.korotin.quizlet.repository.CardSetRepository;
import com.korotin.quizlet.service.CardSetService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@AllArgsConstructor
public class MainController {

    private final CardSetRepository cardSetRepository;

    private final CardSetService cardSetService;


    @GetMapping("/home")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.isAuthenticated());
        if (!auth.isAuthenticated()) {
            return "home";
        }

        List<CardSet> ownedCardSets = cardSetService.getOwnedSetsByUsername(auth.getName());
        List<CardSet> editedCardSets = cardSetService.getSetsWhereUserIsEditorByUsername(auth.getName());

        System.out.println("owned " + ownedCardSets.size());
        System.out.println("edited " + editedCardSets.size());

        model.addAttribute("ownedSets", ownedCardSets);
        model.addAttribute("editedSets", editedCardSets);

        return "home";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }

}
