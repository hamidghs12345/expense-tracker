package com.snapppay.expensetracker.domain.model;


public enum UserAuthority {
  USER;

  public static UserAuthority getDefault() {
    return USER;
  }
}