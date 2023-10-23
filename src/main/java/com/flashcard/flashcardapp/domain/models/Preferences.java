package com.flashcard.flashcardapp.domain.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Preferences {
    private int userId;
    private String interfaceLanguage;
    private int volume;
    private boolean audioOn;
    private boolean hintsOn;
    private boolean romajiOn;
    private boolean darkMode;
}
