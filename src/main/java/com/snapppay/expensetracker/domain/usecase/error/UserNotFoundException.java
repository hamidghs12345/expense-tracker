package com.snapppay.expensetracker.domain.usecase.error;


import com.snapppay.expensetracker.domain.error.Error;
import com.snapppay.expensetracker.domain.error.ErrorEnum;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends Error {

  public UserNotFoundException() {
    super(ErrorEnum.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
  }

}
