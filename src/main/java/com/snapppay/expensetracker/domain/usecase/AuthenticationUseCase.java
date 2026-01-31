package com.snapppay.expensetracker.domain.usecase;

import ch.qos.logback.core.util.StringUtil;
import com.snapppay.expensetracker.domain.error.Error.RequiredFieldException;
import com.snapppay.expensetracker.domain.error.ErrorEnum;
import com.snapppay.expensetracker.domain.model.AuthenticationInfo;
import com.snapppay.expensetracker.domain.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationUseCase {

  private final AuthenticationService authenticationService;

  public Pair<String, String> authenticate(AuthenticationInfo authenticationInfo) {
    String mobile = authenticationInfo.getMobile();

    if (StringUtil.isNullOrEmpty(mobile)) {
      throw new RequiredFieldException(ErrorEnum.LOGIN_INFO_REQUIRED);
    }

    if (authenticationInfo.getPassword() == null || authenticationInfo.getPassword().isBlank()) {
      throw new RequiredFieldException(ErrorEnum.LOGIN_INFO_REQUIRED);
    }

    return authenticationService.checkAuthenticationAndGetToken(
        authenticationInfo
    );
  }

  public Pair<String, String> refreshToken(String refreshToken) {
    if (refreshToken == null) {
      throw new RequiredFieldException(ErrorEnum.REFRESH_TOKEN_REQUIRED);
    }

    return authenticationService.refreshToken(refreshToken);
  }
}
