package com.korotin.quizlet.service;

import com.korotin.quizlet.domain.CardSet;
import com.korotin.quizlet.domain.User;
import com.korotin.quizlet.exception.SetNotFoundException;
import com.korotin.quizlet.repository.CardSetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CardSetService {

    private final CardSetRepository repository;

    public boolean userHaveRightsToModify(UUID setId, User user) throws SetNotFoundException {
        Optional<CardSet> cardSet = repository.findById(setId);

        if (cardSet.isEmpty())
            throw new SetNotFoundException(setId);

        CardSet set = cardSet.get();

        return set.getOwner().equals(user) || set.getEditors().contains(user);
    }


}
