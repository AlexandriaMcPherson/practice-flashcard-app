package com.flashcard.flashcardapp.infrastructure;

import org.apache.ibatis.annotations.Mapper;

import com.flashcard.flashcardapp.domain.models.User;

@Mapper
public interface UserMapper {
    User findUserByUsername(String username);
}
