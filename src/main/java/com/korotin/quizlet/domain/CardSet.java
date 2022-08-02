package com.korotin.quizlet.domain;

import com.korotin.quizlet.repository.CardRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class CardSet {
    @Id
    private String id;

    private String title;

    private String description = "";

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "set_id")
    private List<Card> cards;

    @Transient
    @NonNull
    private CardRepository repository;

    public Iterable<Card> findAll() {
        return repository.findBySetId(id);
    }
}
