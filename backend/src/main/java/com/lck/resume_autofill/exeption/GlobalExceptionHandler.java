package com.lck.resume_autofill.exeption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResumeParseException.class)
    public ResponseEntity<String> handleResumeError(
            ResumeParseException ex) {

        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}