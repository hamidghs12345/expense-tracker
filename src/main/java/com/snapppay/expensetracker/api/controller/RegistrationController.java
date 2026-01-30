package com.snapppay.expensetracker.api.controller;

import com.snapppay.expensetracker.api.dto.OtpResponse;
import com.snapppay.expensetracker.api.dto.RegistrationCodeRequest;
import com.snapppay.expensetracker.domain.usecase.RegistrationCodeUseCase;
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

  @PostMapping("/generate-otp")
  public ResponseEntity<OtpResponse> generateOtpCode(
      @RequestBody RegistrationCodeRequest request
  ) {
    String code = registrationCodeUseCase.generateAndSentOtpCode(request.mobile());

    return ResponseEntity.ok(new OtpResponse(code));
  }

}
