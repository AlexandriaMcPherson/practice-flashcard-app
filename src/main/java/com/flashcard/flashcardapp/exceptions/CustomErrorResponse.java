package com.flashcard.flashcardapp.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomErrorResponse {
    private int status;
    private String error;
}
