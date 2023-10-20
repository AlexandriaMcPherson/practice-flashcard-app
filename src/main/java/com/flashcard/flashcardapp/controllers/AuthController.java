package com.flashcard.flashcardapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashcard.flashcardapp.domain.models.User;
import com.flashcard.flashcardapp.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    
    @PostMapping("/token")
    public String token(Authentication authentication) {
        return authService.issueToken(authentication);
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody String username, String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        authService.addUser(newUser);
        return newUser;
    }

}
