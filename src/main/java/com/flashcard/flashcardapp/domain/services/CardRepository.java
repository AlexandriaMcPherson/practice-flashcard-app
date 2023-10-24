package com.flashcard.flashcardapp.domain.services;

import java.util.List;
import java.util.Optional;

import com.flashcard.flashcardapp.domain.models.Card;

public interface CardRepository {
    List<Card> getAllCards();

    List<Card> getDueCards();

    void addCard(Card card);

    Optional<Card> findCardByFront(String cardFront);

    void reviewCard(Card card);

    void activateCardSet(int lessonId);

    Optional<Integer> getMaxId();
}
