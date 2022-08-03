package com.korotin.quizlet.repository;

import com.korotin.quizlet.domain.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CardRepository extends CrudRepository<Card, UUID> {
}
