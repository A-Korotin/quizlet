package com.korotin.quizlet.domain;

import com.korotin.quizlet.domain.security.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSet {
    @Id
    @GeneratedValue
    private UUID id;

    @Type(type = "org.hibernate.type.TextType")
    private String title;

    @Lob
    private String description = "";

    private Boolean isPublic;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "set_id")
    private List<Card> cards;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> editors = new ArrayList<>();

    public void clearEmptyCards() {
        cards.removeIf(c -> {
            String term = c.getTerm();
            String definition = c.getDefinition();
            return  term == null   || definition == null ||
                    term.isEmpty() || definition.isEmpty();
        });

    }
}
