package com.flashcard.flashcardapp.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flashcard.flashcardapp.domain.models.Word;
import com.flashcard.flashcardapp.domain.services.DictionaryDomainService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DictionaryService {
    
    private final DictionaryDomainService dictionaryDomainService;

    public List<Word> browseDictionary() {
        return dictionaryDomainService.browseDictionary();
    }

    public List<Word> searchDictionary(String query) {
        return dictionaryDomainService.searchDictionary(query);
    }
}
