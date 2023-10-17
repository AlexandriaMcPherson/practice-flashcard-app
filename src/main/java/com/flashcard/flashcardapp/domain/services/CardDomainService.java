package com.flashcard.flashcardapp.domain.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flashcard.flashcardapp.domain.models.Card;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardDomainService {

    private final CardRepository cardRepository;
    
    public List<Card> getAllCards() {
        return cardRepository.getAllCards();
    }

    public List<Card> getDueCards() {
        return cardRepository.getDueCards();
    }

    public Card addCard(Card card) {
        Optional<Integer> currentMaxId = Optional.of(1);
        var newid = currentMaxId.orElse(0) + 1;
        card.setId(newid);

        var timeDue = new Timestamp(System.currentTimeMillis());
        card.setTimeDue(timeDue);

        card.setReviewInterval(1.0);
        card.setCorrectInARow(0);
        card.setEase(2.5F);

        return cardRepository.addCard(card);
    }

    public void reviewCard(Card card, int score) {

        // If fail
        if (score == 1) {
            // Reset correct in row
            card.setCorrectInARow(0);
        } else {
            // Update correct in a row
            card.setCorrectInARow(card.getCorrectInARow() + 1);
        }
        // Set new interval
        double newInterval = CardFunctions.calculateNewInterval(card.getReviewInterval(), card.getEase(), score);
        card.setReviewInterval(newInterval);

        // Set new ease
        card.setEase(CardFunctions.calculateNewEase(card.getEase(), score));

        // Set new time due
        long newTimeDueMillis = CardFunctions.calculateNewTimeMillis(card.getReviewInterval());
        var timeDue = new Timestamp(newTimeDueMillis);
        card.setTimeDue(timeDue);
        
        // Update database
        cardRepository.reviewCard(card);
        
    }

}
