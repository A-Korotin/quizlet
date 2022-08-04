package com.korotin.quizlet.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Card {
    @NonNull
    @Id
    @GeneratedValue
    private UUID id;

    @NonNull
    private String term;

    @NonNull
    private String definition;
}
