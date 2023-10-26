package com.flashcard.flashcardapp.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashcard.flashcardapp.domain.models.Card;
import com.flashcard.flashcardapp.exceptions.BadInputException;
import com.flashcard.flashcardapp.exceptions.CardExistsException;
import com.flashcard.flashcardapp.exceptions.CustomErrorResponse;
import com.flashcard.flashcardapp.services.CardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cards")
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

    @PutMapping("/review")
    public void reviewCard(Card card, int score) {
        cardService.reviewCard(card, score);
    }

    @PutMapping("/activate")
    public void activateCardSet(int lessonId) {
        // Set all cards belonging to a certain lesson active
        // by changing the due date to now
        cardService.activateCardSet(lessonId);
    } 

    @PostMapping("/add")
    public ResponseEntity<Card> addCard(@RequestBody Card card) {
        Card responseCard = cardService.addCard(card);
        return ResponseEntity.ok(responseCard);
    }
    
}
