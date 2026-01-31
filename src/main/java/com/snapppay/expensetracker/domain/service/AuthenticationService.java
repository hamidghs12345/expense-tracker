package com.snapppay.expensetracker.domain.service;


import com.snapppay.expensetracker.domain.model.AuthenticationInfo;
import com.snapppay.expensetracker.domain.usecase.Pair;

public interface AuthenticationService {

  Pair<String, String> checkAuthenticationAndGetToken(AuthenticationInfo authenticationInfo);

  Pair<String, String> refreshToken(String refreshToken);

}
