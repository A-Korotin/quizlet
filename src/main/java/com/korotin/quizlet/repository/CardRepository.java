package com.korotin.quizlet.repository;

import com.korotin.quizlet.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, UUID> {
}
