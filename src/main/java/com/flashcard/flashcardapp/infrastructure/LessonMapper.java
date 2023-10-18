package com.flashcard.flashcardapp.infrastructure;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.flashcard.flashcardapp.domain.models.Lesson;

@Mapper
public interface LessonMapper {
    
    List<Lesson> getAllLessons();

}
