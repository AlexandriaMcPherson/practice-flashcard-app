package com.flashcard.flashcardapp.domain.services;

import org.springframework.stereotype.Service;

import com.flashcard.flashcardapp.domain.models.Preferences;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PreferencesDomainService {
    private final PreferencesRepository preferencesRepository;
    
    public Preferences showPreferences() {
        return preferencesRepository.showPreferences();
    }

    public void updatePreferences(Preferences preferences) {
        preferencesRepository.updatePreferences(preferences);
    }
}
