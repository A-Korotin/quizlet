package com.korotin.quizlet.controller;

import com.korotin.quizlet.domain.CardSet;
import com.korotin.quizlet.domain.security.User;
import com.korotin.quizlet.repository.CardSetRepository;
import com.korotin.quizlet.service.CardSetService;
import com.korotin.quizlet.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
    private final UserService userService;

    @GetMapping("/create")
    public String create(Model model,
                         @RequestParam(defaultValue = "3") int numberOfCards) {

        numberOfCards = setValueInValidRange(numberOfCards);

        model.addAttribute("set", new CardSet());
        model.addAttribute("nCards", numberOfCards);
        return "createSet";
    }

    @PostMapping("/create")
    public String addSet(@ModelAttribute CardSet cardSet) {
        cardSet.getCards().removeIf(c -> c.getTerm().isEmpty());

        cardSet.setOwner(getCurrentUser());

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
        String filterLowerCase = filter.toLowerCase();
        List<CardSet> filteredSets = cardSetRepository.findAll()
                .stream().filter(set -> {
                    String title = set.getTitle().toLowerCase();
                    String description = set.getDescription().toLowerCase();
                    return  title.contains(filterLowerCase) ||
                            description.contains(filterLowerCase);
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

        boolean allowedToModify = cardSetService.userHaveRightsToModify(id, getCurrentUser());

        model.addAttribute("userIsEditor", allowedToModify);
        model.addAttribute("set", cardSetOptional.get());
        return "viewSet";
    }

    @DeleteMapping("/{id}")
    public String deleteSet(@PathVariable UUID id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        if (!cardSetService.userHaveRightsToModify(id, authentication.getName())) {
//            // todo no rights to access page
//            return "redirect:/home";
//        }

        if (!cardSetRepository.existsById(id)) {
            // todo not found page
            return "redirect:/home";
        }

        cardSetRepository.deleteById(id);

        return "redirect:/home";
    }

    @GetMapping("/{id}/edit")
    public String editSet(Model model,
                          @PathVariable UUID id,
                          @RequestParam(defaultValue = "3") int numberOfCards) {

        numberOfCards = setValueInValidRange(numberOfCards);

        Optional<CardSet> cardSet = cardSetRepository.findById(id);

        if (cardSet.isEmpty()) {
            // todo not found page
            return "redirect:/home";
        }

        model.addAttribute("set", cardSet.get());
        model.addAttribute("nCards", numberOfCards);

        return "editSet";
    }

    @GetMapping("/{id}/editors")
    public String manageEditors(Model model,
                                @PathVariable UUID id) {

        Optional<CardSet> cardSet = cardSetRepository.findById(id);

        if (cardSet.isEmpty()) {
            // todo not found page
            return "redirect:/home";
        }

        CardSet set = cardSet.get();
        model.addAttribute("set", set);

        return "manageEditors";
    }

    @PutMapping("/{id}/editors")
    public String confirmEditors(@PathVariable UUID id,
                                 @ModelAttribute CardSet editedSet) {
        List<User> editors = editedSet.getEditors()
                .stream().filter(user -> user.getUsername() != null && !user.getUsername().equals(""))
                .filter(user -> {
                    String username = user.getUsername();
                    try {
                        User userFromDb = (User) userService.loadUserByUsername(username);
                        return true;
                    } catch (UsernameNotFoundException e) {
                        return false;
                    }
                })
                .map(user -> (User) userService.loadUserByUsername(user.getUsername()))
                .collect(Collectors.toList());

        System.out.println(editors);

        Optional<CardSet> cardSetOptional = cardSetRepository.findById(id);

        if (cardSetOptional.isEmpty()) {
            // TODO: 23.08.2022 not found page
            return "redirect:/home";
        }

        CardSet set = cardSetOptional.get();
        set.setEditors(editors);
        System.out.println(cardSetRepository.save(set));


        return "redirect:/home";
    }

    @PutMapping("/{id}")
    public String confirmEdit(@PathVariable UUID id,
                              @ModelAttribute CardSet editedCardSet) {

        // todo check owner/editor of chosen set
        System.out.println(editedCardSet);
        editedCardSet.clearEmptyCards();
        editedCardSet.setOwner(getCurrentUser());
        System.out.println(cardSetRepository.save(editedCardSet));
        return "redirect:/home";
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) userService.loadUserByUsername(authentication.getName());
    }

    private int setValueInValidRange(int value) {
        value = Math.max(1, value);
        value = Math.min(100, value);
        return value; // if 1 <= number <= 100 return number
    }

}
