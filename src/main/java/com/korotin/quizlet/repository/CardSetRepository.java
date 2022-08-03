package com.korotin.quizlet.repository;

import com.korotin.quizlet.domain.CardSet;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CardSetRepository extends CrudRepository<CardSet, UUID> {}
