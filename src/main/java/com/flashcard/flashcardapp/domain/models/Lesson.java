package com.flashcard.flashcardapp.domain.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Lesson {
    private int lessonId;
    private String title;
    private String description;

}
