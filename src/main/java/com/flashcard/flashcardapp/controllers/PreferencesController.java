package com.flashcard.flashcardapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flashcard.flashcardapp.domain.models.Preferences;
import com.flashcard.flashcardapp.services.PreferencesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/preferences")
@RequiredArgsConstructor
public class PreferencesController {
    private final PreferencesService preferencesService;

    @GetMapping("/")
    public Preferences showPreferences() {
        return preferencesService.showPreferences();
    }

    @PutMapping("/update")
    public void updatePreferences(Preferences preferences) {
        preferencesService.updatePreferences(preferences);
    }
}
