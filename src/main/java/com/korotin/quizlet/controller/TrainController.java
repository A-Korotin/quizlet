package com.korotin.quizlet.controller;

import com.korotin.quizlet.domain.CardSet;
import com.korotin.quizlet.repository.CardSetRepository;
import com.korotin.quizlet.service.CardSetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class TrainController {

    private CardSetRepository cardSetRepository;
    private CardSetService cardSetService;


    @GetMapping("/sets/{id}/train")
    public String train(Model model,
                        @PathVariable UUID id) {

        Optional<CardSet> cardSetOptional = cardSetRepository.findById(id);

        if (cardSetOptional.isEmpty()) {
            // todo not found
            return "redirect:/home";
        }

        CardSet set = cardSetOptional.get();

        model.addAttribute("cards", set.getCards());
        model.addAttribute("name", set.getTitle());
        return "train";
    }
}
