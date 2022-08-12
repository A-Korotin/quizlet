package com.korotin.quizlet.service;

import com.korotin.quizlet.domain.CardSet;
import com.korotin.quizlet.domain.security.User;
import com.korotin.quizlet.exception.SetNotFoundException;
import com.korotin.quizlet.repository.CardSetRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardSetService {

    private final CardSetRepository repository;
    private final UserService userService;

    public List<CardSet> getOwnedSetsByUsername(String username) throws UsernameNotFoundException {

        return repository.findAll().stream()
                .filter(set -> set.getOwner().getUsername().equals(username))
                .collect(Collectors.toList());
    }

    public List<CardSet> getSetsWhereUserIsEditorByUsername(String username) {
        return repository.findAll().stream()
                .filter(set -> set.getEditors().stream()
                        .anyMatch(user -> user.getUsername().equals(username)))
                .collect(Collectors.toList());
    }

    public boolean userHaveRightsToModify(UUID setId, User user) throws SetNotFoundException {
        Optional<CardSet> cardSet = repository.findById(setId);

        if (cardSet.isEmpty())
            throw new SetNotFoundException(setId);

        CardSet set = cardSet.get();

        return  set.getOwner().equals(user) ||
                set.getEditors().contains(user);
    }
}
