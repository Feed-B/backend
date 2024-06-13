package com.example.team_12_be.image.exception;

public class ProjectImageException extends RuntimeException {

    ImgErrorCode errorCode;

    public ProjectImageException(ImgErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    public ProjectImageException(ImgErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }
    public ImgErrorCode getImgErrorCode() {
        return errorCode;
    }
}
