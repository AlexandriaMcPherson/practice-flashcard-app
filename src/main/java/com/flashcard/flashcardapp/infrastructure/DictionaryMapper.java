package com.flashcard.flashcardapp.infrastructure;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.flashcard.flashcardapp.domain.models.Word;

@Mapper
public interface DictionaryMapper {
    List<Word> browseDictionary();
    List<Word> searchEnglish(String query);
    List<Word> searchFurigana(String query);
    List<Word> searchJapanese(String query);
}
