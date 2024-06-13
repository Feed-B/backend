package com.example.team_12_be.image.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ProjectImageExceptionHandler {

    @ExceptionHandler(ProjectImageException.class)
    public ResponseEntity<String> handleProjectImageException(ProjectImageException e) {
        log.error("ProjectImageException occurred: ",e);
        return new ResponseEntity<>(e.getImgErrorCode().getMessage() , e.getImgErrorCode().getStatus());
    }
}
