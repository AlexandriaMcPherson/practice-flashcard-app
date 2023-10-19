package com.flashcard.flashcardapp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flashcard.flashcardapp.domain.models.Preferences;
import com.flashcard.flashcardapp.domain.services.PreferencesDomainService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor

public class PreferencesService {
    private final PreferencesDomainService preferencesDomainService;
    
    public Preferences showPreferences() {
        return preferencesDomainService.showPreferences();
    }

    public void updatePreferences(Preferences preferences) {
        preferencesDomainService.updatePreferences(preferences);
    }

}
