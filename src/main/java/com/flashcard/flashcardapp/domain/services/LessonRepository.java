package com.flashcard.flashcardapp.domain.services;

import java.util.List;

import com.flashcard.flashcardapp.domain.models.Lesson;

public interface LessonRepository {
    
    List<Lesson> getAllLessons();

}
