package com.korotin.quizlet;

import com.korotin.quizlet.domain.Card;
import com.korotin.quizlet.domain.Content;
import com.korotin.quizlet.domain.ContentType;
import com.korotin.quizlet.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {

    private CardRepository repository;

    @Autowired
    public TestController(CardRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/test")
    public void test() {
        Content content = new Content("123", ContentType.PLAIN_TEXT, new byte[]{1, 2, 3});
        Card card = new Card("1", "Test term", List.of(content));
        repository.save(card);
    }
}
