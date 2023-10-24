package com.flashcard.flashcardapp.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CardExistsException extends ResponseStatusException {
    
    public CardExistsException(String message) {
        super(HttpStatus.CONFLICT, message);
    }

    @Override
    public HttpHeaders getResponseHeaders() {
        // TODO figure out what should be included in headers
        return new HttpHeaders();
    }

}
