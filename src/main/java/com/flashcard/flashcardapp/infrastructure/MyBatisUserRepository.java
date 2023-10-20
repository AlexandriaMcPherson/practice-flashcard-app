package com.flashcard.flashcardapp.infrastructure;

import org.springframework.stereotype.Repository;

import com.flashcard.flashcardapp.domain.models.User;
import com.flashcard.flashcardapp.domain.services.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisUserRepository implements UserRepository{
    private final UserMapper userMapper;

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }
    
}
