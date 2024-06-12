package com.example.team_12_be.image.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectImageExceptionHandler {

    @ExceptionHandler(ProjectImageException.class)
    public ResponseEntity<String> handleProjectImageException(ProjectImageException e) {
        return new ResponseEntity<>(e.getImgErrorCode().getMessage() , e.getImgErrorCode().getStatus());
    }
}
