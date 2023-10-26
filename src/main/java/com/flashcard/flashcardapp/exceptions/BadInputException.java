package com.flashcard.flashcardapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class BadInputException extends ResponseStatusException{

    public BadInputException(HttpStatusCode status, String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
    
}
