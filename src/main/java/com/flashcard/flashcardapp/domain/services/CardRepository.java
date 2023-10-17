package com.flashcard.flashcardapp.domain.services;

import java.util.List;
import java.util.Optional;

import com.flashcard.flashcardapp.domain.models.Card;

public interface CardRepository {
    List<Card> getAllCards();

    List<Card> getDueCards();

    Card addCard(Card card);

    Optional<Integer> getMaxId();
}
