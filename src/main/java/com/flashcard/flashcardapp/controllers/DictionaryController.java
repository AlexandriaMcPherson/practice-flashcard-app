package com.flashcard.flashcardapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flashcard.flashcardapp.domain.models.Word;
import com.flashcard.flashcardapp.services.DictionaryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/dictionary")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;
    
    @GetMapping("/browse")
    public List<Word> browseDictionary() {
        return dictionaryService.browseDictionary();
    }

    @GetMapping("/search")
    public List<Word> searchDictionary(@RequestParam String query) {
        return dictionaryService.searchDictionary(query);
    }
}
