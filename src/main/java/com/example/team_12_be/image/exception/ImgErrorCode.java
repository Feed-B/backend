package com.example.team_12_be.image.exception;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;

public enum ImgErrorCode {
    NO_FILE_EXTENTION(HttpStatus.INTERNAL_SERVER_ERROR, "파일형태가 아닙니다."),
    INVALID_FILE_EXTENTION(HttpStatus.INTERNAL_SERVER_ERROR , "파일 확장자를 확인해주세요."),
    EMPTY_FILE_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR , "파일이 없습니다."),
    IO_EXCEPTION_ON_IMAGE_UPLOAD(HttpStatus.INTERNAL_SERVER_ERROR , "이미지 업로드 중 문제 발생."),
    PUT_OBJECT_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR , "S3에 PUT_OBJECT 과정에서 문제 발생");
    private final HttpStatus status;
    private final String message;

    ImgErrorCode(HttpStatus status,  String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
