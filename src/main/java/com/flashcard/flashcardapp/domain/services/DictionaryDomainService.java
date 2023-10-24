package com.flashcard.flashcardapp.domain.services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.flashcard.flashcardapp.domain.models.Word;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DictionaryDomainService {

    private final DictionaryRepository dictionaryRepository;
    
    public List<Word> browseDictionary() {
        return dictionaryRepository.browseDictionary();
    }

    public List<Word> searchDictionary(String query) {
        Pattern en = Pattern.compile("[a-zA-Z]+");
        Matcher enMatcher = en.matcher(query);
        Pattern kana = Pattern.compile("^[\u3040-\u309F]+$");
        Matcher kanaMatcher = kana.matcher(query);
        // Check if query is English or Japanese
        if (enMatcher.find()) {
            return dictionaryRepository.searchEnglish(query);
        } else if (kanaMatcher.find()) {
            return dictionaryRepository.searchFurigana(query);
        } else {
            return dictionaryRepository.searchJapanese(query);
        }
    }

}
