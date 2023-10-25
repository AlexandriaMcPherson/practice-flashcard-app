package com.flashcard.flashcardapp.infrastructure;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.flashcard.flashcardapp.domain.models.Card;

@Mapper
public interface CardMapper {
    List<Card> getAllCards();
    List<Card> getDueCards();
    void addCard(Card card);
    Optional<Card> getCardByFront(String cardFront);
    void reviewCard(Card card);
    void activateCardSet(int lessonId);
    Optional<Integer> getMaxId();
}
