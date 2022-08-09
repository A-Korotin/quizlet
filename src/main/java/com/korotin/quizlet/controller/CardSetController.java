package com.korotin.quizlet.controller;

import com.korotin.quizlet.domain.CardSet;
import com.korotin.quizlet.repository.CardSetRepository;
import com.korotin.quizlet.service.CardSetService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sets")
@AllArgsConstructor
public class CardSetController {

    private final CardSetRepository cardSetRepository;
    private final CardSetService cardSetService;

    @GetMapping("/create")
    public String create(Model model,
                         @RequestParam(defaultValue = "3") @Min(0) int numberOfCards) {
        model.addAttribute("set", new CardSet());
        model.addAttribute("nCards", numberOfCards);
        return "createSet";
    }

    @PostMapping("/create")
    public String addSet(@ModelAttribute CardSet cardSet) {
        cardSet.getCards().removeIf(c -> c.getTerm().isEmpty());

        System.out.println(cardSetRepository.save(cardSet));
        return "redirect:/home";
    }

    // /sets
    @GetMapping
    public String allSets(Model model,
                          @RequestParam(required = false) String filter) {
        List<CardSet> sets = cardSetRepository.findAll();

        // no specific filter
        if (filter == null) {
            model.addAttribute("sets", sets);
            return "allSets";
        }

        List<CardSet> filteredSets = cardSetRepository.findAll()
                .stream().filter(set -> {
                    String title = set.getTitle();
                    String description = set.getDescription();
                    return  title.contains(filter) ||
                            description.contains(filter);
                }).collect(Collectors.toList());

        model.addAttribute("sets", filteredSets);
        return "allSets";
    }

    @GetMapping("/{id}")
    public String viewSet(Model model,
                          @PathVariable UUID id) {

        Optional<CardSet> cardSetOptional = cardSetRepository.findById(id);

        if (cardSetOptional.isEmpty()) {
            // todo not found page
            return "redirect:/home";
        }

        model.addAttribute("set", cardSetOptional.get());
        return "viewSet";
    }

    @GetMapping("/{id}/edit")
    public String editSet(Model model,
                          @PathVariable UUID id,
                          @RequestParam(defaultValue = "3") @Min(0) int nCards) {


        Optional<CardSet> cardSet = cardSetRepository.findById(id);

        if (cardSet.isEmpty()) {
            // todo not found page
            return "redirect:/home";
        }

        model.addAttribute("set", cardSet.get());
        model.addAttribute("nCards", nCards);

        return "editSet";
    }

    @PostMapping("/{id}/edit")
    public String confirmEdit(@PathVariable UUID id,
                              @ModelAttribute CardSet editedCardSet) {

        // todo check owner/editor of chosen set
        System.out.println(editedCardSet);
        editedCardSet.clearEmptyCards();
        System.out.println(cardSetRepository.save(editedCardSet));
        return "redirect:/home";
    }

}
