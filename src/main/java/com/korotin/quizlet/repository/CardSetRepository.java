package com.korotin.quizlet.repository;

import com.korotin.quizlet.domain.CardSet;
import org.springframework.data.repository.CrudRepository;

public interface CardSetRepository extends CrudRepository<CardSet, String> {}
