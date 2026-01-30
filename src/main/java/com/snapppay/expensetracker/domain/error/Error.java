package com.snapppay.expensetracker.domain.error;

import org.springframework.http.HttpStatus;

public abstract class Error extends RuntimeException {

  private final ErrorEnum errorEnum;
  private final HttpStatus httpStatus;

  protected Error(ErrorEnum errorsEnum) {
    this.errorEnum = errorsEnum;
    this.httpStatus = HttpStatus.valueOf(errorsEnum.getStatus());
  }

  protected Error(ErrorEnum errorEnum, HttpStatus httpStatus) {
    this.errorEnum = errorEnum;
    this.httpStatus = httpStatus;
  }

  protected Error(String message, ErrorEnum errorEnum, HttpStatus httpStatus) {
    super(message);
    this.errorEnum = errorEnum;
    this.httpStatus = httpStatus;
  }

  public String getErrorMessageKey() {
    if (errorEnum != null) {
      return errorEnum.getMessageKey();
    }

    return null;
  }

  public int getDeveloperCode() {
    if (errorEnum != null) {
      return errorEnum.getCode();
    }

    else return 0;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public static class UnauthorizedException extends Error {

    public UnauthorizedException() {
      super(ErrorEnum.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
    }

    public UnauthorizedException(String message) {
      super(message, ErrorEnum.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
    }
  }

  public static class BadRequestException extends Error {

    public BadRequestException() {
      super(ErrorEnum.GENERAL_BAD_REQUEST, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message) {
      super(message, ErrorEnum.GENERAL_BAD_REQUEST, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(ErrorEnum errorEnum) {
      super(errorEnum, HttpStatus.BAD_REQUEST);
    }

  }

  public static class DuplicateException extends Error {

    public DuplicateException() {
      super(ErrorEnum.GENERAL_DUPLICATION, HttpStatus.CONFLICT);
    }

    public DuplicateException(String message) {
      super(message, ErrorEnum.GENERAL_DUPLICATION, HttpStatus.CONFLICT);
    }

    public DuplicateException(ErrorEnum errorEnum) {
      super(errorEnum, HttpStatus.CONFLICT);
    }

  }


}