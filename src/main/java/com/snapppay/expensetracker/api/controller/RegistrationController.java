package com.snapppay.expensetracker.api.controller;

import com.snapppay.expensetracker.api.dto.AuthenticationResponse;
import com.snapppay.expensetracker.api.dto.OtpResponse;
import com.snapppay.expensetracker.api.dto.RegistrationCodeRequest;
import com.snapppay.expensetracker.api.dto.RegistrationRequest;
import com.snapppay.expensetracker.domain.model.RegistrationInfo;
import com.snapppay.expensetracker.domain.usecase.Pair;
import com.snapppay.expensetracker.domain.usecase.RegistrationCodeUseCase;
import com.snapppay.expensetracker.domain.usecase.UserCreationUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Registration")
@RequestMapping("/api/v1/user/registration")
public class RegistrationController {

  private final RegistrationCodeUseCase registrationCodeUseCase;
  private final UserCreationUseCase userCreationUseCase;


  @PostMapping("/generate-otp")
  public ResponseEntity<OtpResponse> generateOtpCode(
      @RequestBody RegistrationCodeRequest request
  ) {
    String code = registrationCodeUseCase.generateAndSentOtpCode(request.mobile());

    return ResponseEntity.ok(new OtpResponse(code));
  }

  @PostMapping
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegistrationRequest request
  ) {
    RegistrationInfo registrationInfo = RegistrationInfo.builder()
        .mobile(request.mobile())
        .password(request.password())
        .otpCode(request.otpCode())
        .build();

    Pair<String, String > pair = userCreationUseCase.register(registrationInfo);

    AuthenticationResponse response = AuthenticationResponse.builder()
        .accessToken(pair.first())
        .refreshToken(pair.second())
        .build();

    return ResponseEntity.ok(response);
  }

}
