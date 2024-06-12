package com.example.team_12_be.image.exception;

public class ProjectImageException extends RuntimeException {

    ImgErrorCode errorCode;

    public ProjectImageException(ImgErrorCode errorCode) {
        super(errorCode.getMessage());
    }

    public ImgErrorCode getImgErrorCode() {
        return errorCode;
    }
}
