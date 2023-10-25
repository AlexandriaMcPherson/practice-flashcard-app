package com.flashcard.flashcardapp.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.flashcard.flashcardapp.domain.models.Word;
import com.flashcard.flashcardapp.domain.services.DictionaryRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyBatisDictionaryRepository implements DictionaryRepository{

    private final DictionaryMapper dictionaryMapper;
    
    @Override
    public List<Word> browseDictionary() {
        return dictionaryMapper.browseDictionary();
    }

    @Override
    public List<Word> searchEnglish(String query) {
        query = "%" + query + "%";
        return dictionaryMapper.searchEnglish(query);
    }

    @Override
    public List<Word> searchFurigana(String query) {
        query = "%" + query + "%";
        return dictionaryMapper.searchFurigana(query);
    }

    @Override
    public List<Word> searchJapanese(String query) {
        query = "%" + query + "%";
        return dictionaryMapper.searchJapanese(query);
    }
    
}
