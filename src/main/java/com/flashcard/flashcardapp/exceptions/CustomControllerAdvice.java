package com.flashcard.flashcardapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class CustomControllerAdvice {

    @ExceptionHandler(CardExistsException.class)
    public ResponseEntity<CustomErrorResponse> handleCardFrontExistsException(CardExistsException e) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(e.getMessage());
        error.setStatusCode(HttpStatus.CONFLICT.value());
        error.setStatusName(HttpStatus.CONFLICT.name());
        return new ResponseEntity<CustomErrorResponse>(error, null, error.getStatusCode());
    }

    @ExceptionHandler(BadInputException.class)
    public ResponseEntity<CustomErrorResponse> handleBadInputException(BadInputException e) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(e.getMessage());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setStatusName(HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<CustomErrorResponse>(error, null, error.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> fallbackExceptionHandler(Exception e) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(e.getMessage());
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setStatusName(HttpStatus.INTERNAL_SERVER_ERROR.name());
        return new ResponseEntity<CustomErrorResponse>(error, null, error.getStatusCode());
    }
}
