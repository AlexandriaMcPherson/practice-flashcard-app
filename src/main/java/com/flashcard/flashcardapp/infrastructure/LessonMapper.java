package com.flashcard.flashcardapp.infrastructure;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.flashcard.flashcardapp.domain.models.Lesson;
import com.flashcard.flashcardapp.domain.models.Slide;

@Mapper
public interface LessonMapper {
    
    List<Lesson> getAllLessons();

    Lesson getLesson(int lessonId);

    List<Slide> getSlidesForLesson(int lessonId);

}
