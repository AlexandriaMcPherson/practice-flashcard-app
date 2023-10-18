package com.flashcard.flashcardapp.domain.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Slide {
    private int slideId;
    private int lessonId;
    private String text;
    private String imageLoc;
    private String audioLoc;

}
