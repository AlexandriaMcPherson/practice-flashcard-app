package com.flashcard.flashcardapp.domain.services;

import java.util.List;

import com.flashcard.flashcardapp.domain.models.Word;

public interface DictionaryRepository {
    List<Word> browseDictionary();
    List<Word> searchEnglish(String query);
    List<Word> searchFurigana(String query);
    List<Word> searchJapanese(String query);
}
