package com.korotin.quizlet;

import com.korotin.quizlet.domain.Card;
import com.korotin.quizlet.domain.Content;
import com.korotin.quizlet.domain.ContentType;
import com.korotin.quizlet.repository.CardRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
public class QuizletApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizletApplication.class, args);

    }

}
