package com.korotin.quizlet.repository;

import com.korotin.quizlet.domain.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, String> {
    Iterable<Card> findBySetId(String setId);
}
