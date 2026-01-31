package com.snapppay.expensetracker.api.controller;

import com.snapppay.expensetracker.api.dto.AuthenticationRequest;
import com.snapppay.expensetracker.api.dto.AuthenticationResponse;
import com.snapppay.expensetracker.api.dto.RefreshTokenRequest;
import com.snapppay.expensetracker.domain.model.AuthenticationInfo;
import com.snapppay.expensetracker.domain.usecase.AuthenticationUseCase;
import com.snapppay.expensetracker.domain.usecase.Pair;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication")
@RequestMapping("/api/v1/user/authentication")
public class AuthenticationController {

  private final AuthenticationUseCase authenticationUseCase;

  @PostMapping
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    AuthenticationInfo authenticationInfo = AuthenticationInfo.builder()
        .mobile(request.getMobile())
        .password(request.getPassword())
        .build();

    Pair<String, String> tokens = authenticationUseCase.authenticate(authenticationInfo);

    AuthenticationResponse response = AuthenticationResponse.builder()
        .accessToken(tokens.first())
        .refreshToken(tokens.second())
        .build();

    return ResponseEntity.ok(response);
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<AuthenticationResponse> refreshToken(
      @Valid @RequestBody RefreshTokenRequest request
  ) {
    Pair<String, String> tokens = authenticationUseCase.refreshToken(request.getRefreshToken());

    AuthenticationResponse response = AuthenticationResponse.builder()
        .accessToken(tokens.first())
        .refreshToken(tokens.second())
        .build();

    return ResponseEntity.ok(response);
  }

}
