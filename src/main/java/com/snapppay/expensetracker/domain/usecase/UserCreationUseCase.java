package com.snapppay.expensetracker.domain.usecase;

import com.snapppay.expensetracker.domain.error.Error.BadRequestException;
import com.snapppay.expensetracker.domain.error.ErrorEnum;
import com.snapppay.expensetracker.domain.model.AuthenticationInfo;
import com.snapppay.expensetracker.domain.model.OtpReferenceType;
import com.snapppay.expensetracker.domain.model.RegistrationInfo;
import com.snapppay.expensetracker.domain.model.User;
import com.snapppay.expensetracker.domain.service.AuthenticationService;
import com.snapppay.expensetracker.domain.service.OtpValidationService;
import com.snapppay.expensetracker.domain.service.UserCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreationUseCase {

  private final UserCreationService userCreationService;
  private final AuthenticationService authenticationService;
  private final OtpValidationService otpValidationService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Pair<String, String> register(RegistrationInfo registrationInfo) {
    String mobile = registrationInfo.getMobile();
    String password = registrationInfo.getPassword();

    String encodedPassword = bCryptPasswordEncoder.encode(password);

    if (mobile != null && !isOtpValid(mobile, registrationInfo.getOtpCode())) {
      throw new BadRequestException(ErrorEnum.OTP_INVALID);
    }

    User user = userCreationService.create(
        mobile,
        encodedPassword);

    return generateAccessToken(user, password);
 }

  private Pair<String, String> generateAccessToken(User user, String password) {

    AuthenticationInfo authenticationInfo = AuthenticationInfo.builder()
        .mobile(user.getMobile())
        .password(password)
        .build();

    return authenticationService.checkAuthenticationAndGetToken(authenticationInfo);
  }

  private boolean isOtpValid(String reference, String otpCode) {
    return otpValidationService.isCodeValid(reference, OtpReferenceType.REGISTRATION, otpCode);
  }

}
