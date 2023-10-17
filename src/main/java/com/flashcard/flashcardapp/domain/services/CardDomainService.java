package com.flashcard.flashcardapp.domain.services;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flashcard.flashcardapp.domain.models.Card;
import com.flashcard.flashcardapp.domain.services.CardFunctions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardDomainService {
    
    public List<Card> getAllCards() {
        return Collections.emptyList();
    }

    public List<Card> getDueCards() {
        return Collections.emptyList();
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

        return card;
    }

    public void reviewCard(Card card, int score) {

        // If fail
        if (score == 1) {
            // Reset interval and correct in row
            card.setReviewInterval(0);
            card.setCorrectInARow(0);
            
            // Calculate new ease
            card.setEase(CardFunctions.calculateNewEase(card.getEase(), score));

            // Set new time due
            var timeDue = new Timestamp(System.currentTimeMillis());
            card.setTimeDue(timeDue);
        } else { // If easy, ok, or hard
            // Update correct in a row
            card.setCorrectInARow(card.getCorrectInARow() + 1);
            // Calculate new interval
            double newInterval = CardFunctions.calculateNewInterval(card.getReviewInterval(), card.getEase(), score);
            card.setReviewInterval(newInterval);

            // Calculate new ease
            card.setEase(CardFunctions.calculateNewEase(card.getEase(), score));

            // Time due = now + interval in milliseconds
            long newTimeDueMillis = (long) (System.currentTimeMillis() + (card.getReviewInterval() * 86400000L));
            var timeDue = new Timestamp(newTimeDueMillis);
            card.setTimeDue(timeDue);
        }
    }

}
