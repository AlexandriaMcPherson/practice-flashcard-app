package com.flashcard.flashcardapp.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flashcard.flashcardapp.domain.models.Card;
import com.flashcard.flashcardapp.domain.services.CardDomainService;

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
        return cardDomainService.addCard(card);
    }
}
