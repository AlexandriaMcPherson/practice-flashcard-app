package com.flashcard.flashcardapp.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.flashcard.flashcardapp.domain.models.Lesson;
import com.flashcard.flashcardapp.domain.models.Slide;
import com.flashcard.flashcardapp.domain.services.LessonRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisLessonRepository implements LessonRepository{

    private final LessonMapper lessonMapper;

    @Override
    public List<Lesson> getAllLessons() {
        return lessonMapper.getAllLessons();
    }

    @Override
    public Lesson getLesson(int lessonID) {
        return lessonMapper.getLesson(lessonID);
    }

    @Override
    public List<Slide> getSlidesForLesson(int lessonId) {
        return lessonMapper.getSlidesForLesson(lessonId);
    }
    
}
