package com.flashcard.flashcardapp.domain.services;

import com.flashcard.flashcardapp.domain.models.User;

public interface UserRepository {
    User findUserByUsername(String username);
}
