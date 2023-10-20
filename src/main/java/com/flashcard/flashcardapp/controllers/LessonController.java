package com.flashcard.flashcardapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashcard.flashcardapp.domain.models.Lesson;
import com.flashcard.flashcardapp.domain.models.LessonWithSlides;
import com.flashcard.flashcardapp.services.LessonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {
    
    private final LessonService lessonService;

    @GetMapping("/")
    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/{lessonId}")
    public LessonWithSlides viewLesson(@PathVariable int lessonId) {
        return lessonService.viewLesson(lessonId);
    }

}
