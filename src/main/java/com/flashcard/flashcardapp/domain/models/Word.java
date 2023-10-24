package com.flashcard.flashcardapp.domain.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Word {
    private String wordJapanese;
    private String wordFurigana;
    private String wordEnglish;
    private String exampleJapanese;
    private String exampleFurigana;
    private String exampleEnglish;
}
