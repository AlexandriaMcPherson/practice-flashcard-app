package com.flashcard.flashcardapp.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updatePreferences(@RequestBody Preferences preferences) {
        preferencesService.updatePreferences(preferences);
    }
}
