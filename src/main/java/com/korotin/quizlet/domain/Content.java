package com.korotin.quizlet.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Content {
    @NonNull
    @Id
    @GeneratedValue
    private UUID id;

    @NonNull
    @Enumerated(EnumType.STRING)
    private ContentType type;

    @NonNull
    private byte[] data;


    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;
}
