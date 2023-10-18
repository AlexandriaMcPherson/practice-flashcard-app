package com.flashcard.flashcardapp.domain.models;

import java.sql.Timestamp;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Card {
    private int id;
    private int lessonId;
    private String cardFront;
    private String cardBack;
    private String notes;
    private Timestamp timeDue;
    private double reviewInterval;
    private int correctInARow;
    private float ease;
}
