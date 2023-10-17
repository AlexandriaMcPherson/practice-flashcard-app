package com.flashcard.flashcardapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashcard.flashcardapp.domain.models.Card;
import com.flashcard.flashcardapp.services.CardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@CrossOrigin
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/browse")
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/study")
    public List<Card> getDueCards() {
        return cardService.getDueCards();
    }

    @PostMapping("/add")
    public Card addCard(@RequestBody Card card) {
        return cardService.addCard(card);
    }
    
}
