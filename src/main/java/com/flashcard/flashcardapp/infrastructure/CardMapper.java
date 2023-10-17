package com.flashcard.flashcardapp.infrastructure;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.flashcard.flashcardapp.domain.models.Card;

@Mapper
public interface CardMapper {
    List<Card> getAllCards();
    List<Card> getDueCards();
    Card addCard(Card card);
    void reviewCard(Card card);
    Optional<Integer> getMaxId();
}
