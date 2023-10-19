package com.flashcard.flashcardapp.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.flashcard.flashcardapp.domain.models.Lesson;
import com.flashcard.flashcardapp.domain.models.LessonWithSlides;

@Service
@RequiredArgsConstructor
public class LessonDomainService {

    private final LessonRepository lessonRepository;
    
    public List<Lesson> getAllLessons() {
        return lessonRepository.getAllLessons();
    }

    public LessonWithSlides viewLesson(int lessonId) {
        LessonWithSlides lesson = new LessonWithSlides();
        lesson.setLesson(lessonRepository.getLesson(lessonId));
        lesson.setSlides(lessonRepository.getSlidesForLesson(lessonId));
        return lesson;
    }

}
