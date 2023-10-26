package com.flashcard.flashcardapp.exceptions;

import java.io.StringWriter;
import java.io.PrintWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
class CustomControllerAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage("Request data missing or unreadable.");
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setStatusName(HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<CustomErrorResponse>(error, null, error.getStatusCode());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CustomErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(e.getMessage());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setStatusName(HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<CustomErrorResponse>(error, null, error.getStatusCode());
    }

    @ExceptionHandler(CardExistsException.class)
    public ResponseEntity<CustomErrorResponse> handleCardFrontExistsException(CardExistsException e) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(e.getReason());
        error.setStatusCode(HttpStatus.CONFLICT.value());
        error.setStatusName(HttpStatus.CONFLICT.name());
        return new ResponseEntity<CustomErrorResponse>(error, null, error.getStatusCode());
    }

    @ExceptionHandler(BadInputException.class)
    public ResponseEntity<CustomErrorResponse> handleBadInputException(BadInputException e) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(e.getReason());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setStatusName(HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<CustomErrorResponse>(error, null, error.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> fallbackExceptionHandler(Exception e) {
        StringWriter stackTraceWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stackTraceWriter);
        e.printStackTrace(pw);
        CustomErrorResponse error = new CustomErrorResponse();
        try {
            error.setMessage(e.getMessage());
        } catch (NullPointerException npe) {
            error.setMessage("no error message recorded");
        }
        error.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setStatusName(HttpStatus.INTERNAL_SERVER_ERROR.name());
        log.error(error.getMessage() + "\n" + stackTraceWriter.toString(), e);
        return new ResponseEntity<CustomErrorResponse>(error, null, error.getStatusCode());
    }
}
