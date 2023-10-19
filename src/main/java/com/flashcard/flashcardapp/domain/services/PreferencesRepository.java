package com.flashcard.flashcardapp.domain.services;

import com.flashcard.flashcardapp.domain.models.Preferences;

public interface PreferencesRepository {
    
    public Preferences showPreferences();

    public void updatePreferences(Preferences preferences);

}
