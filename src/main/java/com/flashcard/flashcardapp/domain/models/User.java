package com.flashcard.flashcardapp.domain.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
}
