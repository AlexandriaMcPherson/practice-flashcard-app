package com.flashcard.flashcardapp.domain.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.flashcard.flashcardapp.domain.models.Lesson;

@Service
@RequiredArgsConstructor
public class LessonDomainService {

    private final LessonRepository lessonRepository;
    
    public List<Lesson> getAllLessons() {
        return lessonRepository.getAllLessons();
    }

}
