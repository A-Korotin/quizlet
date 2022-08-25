package com.korotin.quizlet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue
    private UUID id;

    @Type(type = "org.hibernate.type.TextType")
    private String term;

    @Type(type = "org.hibernate.type.TextType")
    private String definition;
}
