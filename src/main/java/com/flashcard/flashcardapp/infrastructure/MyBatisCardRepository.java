package com.flashcard.flashcardapp.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.flashcard.flashcardapp.domain.models.Card;
import com.flashcard.flashcardapp.domain.services.CardRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisCardRepository implements CardRepository{

    private final CardMapper cardMapper;

    @Override
    public List<Card> getAllCards() {
        return cardMapper.getAllCards();
    }

    @Override
    public List<Card> getDueCards() {
        return cardMapper.getDueCards();
    }

    @Override
    public Card addCard(Card card) {
        return cardMapper.addCard(card);
    }

    @Override
    public Optional<Integer> getMaxId() {
        return cardMapper.getMaxId();
    }
    
}
