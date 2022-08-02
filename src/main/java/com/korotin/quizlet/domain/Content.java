package com.korotin.quizlet.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Content {
    @NonNull
    @Id
    private String id;

    @NonNull
    private ContentType type;

    @NonNull
    private byte[] data;


    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;
}
