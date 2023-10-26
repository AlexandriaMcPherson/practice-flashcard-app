package com.flashcard.flashcardapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flashcard.flashcardapp.domain.models.Card;
import com.flashcard.flashcardapp.domain.services.CardDomainService;
import com.flashcard.flashcardapp.exceptions.BadInputException;
import com.flashcard.flashcardapp.domain.services.CardFunctions;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CardService {

    private final CardDomainService cardDomainService;

    public List<Card> getAllCards() {
        return cardDomainService.getAllCards();
    }

    public List<Card> getDueCards() {
        return cardDomainService.getDueCards();
    }

    public void reviewCard(Card card, int score) {
        cardDomainService.reviewCard(card, score);
    }

    public void activateCardSet(int lessonId) {
        cardDomainService.activateCardSet(lessonId);
    }

    public Card addCard(Card card) {
        // Check that request is valid
        Optional<String> invalidFieldCheck = CardFunctions.checkFieldValidity(card);
        if (invalidFieldCheck.isPresent()) {
            throw new BadInputException(HttpStatus.BAD_REQUEST, String.valueOf(invalidFieldCheck.get()));
        }
        return cardDomainService.addCard(card);
    }
}
