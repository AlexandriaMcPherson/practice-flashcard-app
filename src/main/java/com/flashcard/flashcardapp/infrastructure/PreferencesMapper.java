package com.flashcard.flashcardapp.infrastructure;

import org.apache.ibatis.annotations.Mapper;

import com.flashcard.flashcardapp.domain.models.Preferences;

@Mapper
public interface PreferencesMapper {
    Preferences showPreferences();
    void updatePreferences(Preferences preferences);
}
