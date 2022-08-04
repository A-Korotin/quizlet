package com.korotin.quizlet.repository;

import com.korotin.quizlet.domain.CardSet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardSetRepository extends JpaRepository<CardSet, UUID> {}
