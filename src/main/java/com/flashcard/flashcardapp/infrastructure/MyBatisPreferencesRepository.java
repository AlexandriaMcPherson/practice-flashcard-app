package com.flashcard.flashcardapp.infrastructure;

import org.springframework.stereotype.Repository;

import com.flashcard.flashcardapp.domain.models.Preferences;
import com.flashcard.flashcardapp.domain.services.PreferencesRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisPreferencesRepository implements PreferencesRepository {

    private final PreferencesMapper preferencesMapper;
    
    @Override
    public Preferences showPreferences() {
        return preferencesMapper.showPreferences();
    }

    @Override
    public void updatePreferences(Preferences preferences) {
        preferencesMapper.updatePreferences(preferences);
    }

}
