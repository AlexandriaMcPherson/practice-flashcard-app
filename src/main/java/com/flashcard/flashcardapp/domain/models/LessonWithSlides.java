package com.flashcard.flashcardapp.domain.models;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LessonWithSlides {
    private Lesson lesson;
    private List<Slide> slides;
}
