package com.korotin.quizlet.controller;

import com.korotin.quizlet.domain.CardSet;
import com.korotin.quizlet.repository.CardSetRepository;
import com.korotin.quizlet.service.CardSetService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Controller
@AllArgsConstructor
public class MainController {

    private final CardSetRepository cardSetRepository;

    private final CardSetService cardSetService;


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
        cardSet.getCards().removeIf(c -> c.getTerm().isEmpty());
        System.out.println(cardSetRepository.save(cardSet));
        return "redirect:/";
    }

    @GetMapping("/sets/{id}")
    public String viewSet(@PathVariable UUID id) {
        System.out.println(cardSetRepository.findAllById(List.of(id)));
        return "redirect:/";
    }

    @GetMapping("/sets/{id}/edit")
    public String editSet(Model model,
                          @PathVariable UUID id,
                          @RequestParam(defaultValue = "3") int nCards) {


        Optional<CardSet> cardSet = cardSetRepository.findById(id);

        if (cardSet.isEmpty()) {
            // todo not found page
            return "redirect:/";
        }

        model.addAttribute("set", cardSet.get());
        model.addAttribute("nCards", nCards);

        return "editSet";
    }

    @PostMapping("/sets/{id}/edit")
    public String confirmEdit(@PathVariable UUID id,
                              @RequestParam(defaultValue = "3") int nCards,
                              @ModelAttribute CardSet editedCardSet) {

        // todo check owner/editor of chosen set
        System.out.println(editedCardSet);
        editedCardSet.clearEmptyCards();
        System.out.println(cardSetRepository.save(editedCardSet));
        return "redirect:/";
    }


}
