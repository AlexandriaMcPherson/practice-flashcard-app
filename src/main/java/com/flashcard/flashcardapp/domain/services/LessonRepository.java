package com.flashcard.flashcardapp.domain.services;

import java.util.List;

import com.flashcard.flashcardapp.domain.models.Lesson;
import com.flashcard.flashcardapp.domain.models.Slide;

public interface LessonRepository {
    
    List<Lesson> getAllLessons();

    Lesson getLesson(int lessonId);

    List<Slide> getSlidesForLesson(int lessonId);

}
