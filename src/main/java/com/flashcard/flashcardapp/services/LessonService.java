package com.flashcard.flashcardapp.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flashcard.flashcardapp.domain.models.Lesson;
import com.flashcard.flashcardapp.domain.services.LessonDomainService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LessonService {

    private final LessonDomainService lessonDomainService;
    
    public List<Lesson> getAllLessons() {
        return lessonDomainService.getAllLessons();
    }

}
