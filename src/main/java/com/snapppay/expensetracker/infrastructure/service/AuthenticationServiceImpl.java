package com.snapppay.expensetracker.infrastructure.service;

import com.snapppay.expensetracker.domain.error.Error.UnauthorizedException;
import com.snapppay.expensetracker.domain.model.AuthenticationInfo;
import com.snapppay.expensetracker.domain.service.AuthenticationService;
import com.snapppay.expensetracker.domain.usecase.Pair;
import com.snapppay.expensetracker.infrastructure.entity.UserEntity;
import com.snapppay.expensetracker.infrastructure.repository.UserRepository;
import com.snapppay.expensetracker.infrastructure.security.JwtTokenService;
import com.snapppay.expensetracker.infrastructure.security.JwtTokenService.TokenRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final JwtTokenService jwtTokenService;

  @Override
  public Pair<String, String> checkAuthenticationAndGetToken(
      AuthenticationInfo authenticationInfo
  ) {

    UserEntity userEntity = userRepository.findByMobile(authenticationInfo.getMobile())
        .orElseThrow(UnauthorizedException::new);

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          userEntity.getMobile(),
          authenticationInfo.getPassword()
      ));
    } catch (Throwable e) {
      throw new UnauthorizedException();
    }

    log.info(
        "User logged in with id: {}, mobile: {}",
        userEntity.getId(),
        authenticationInfo.getMobile());

    return generateToken(userEntity);
  }

  private Pair<String, String> generateToken(UserEntity userEntity) {
    TokenRequest tokenRequest = TokenRequest.builder()
        .userId(userEntity.getId())
        .userUUID(userEntity.getUserId())
        .username(userEntity.getMobile())
        .authority(userEntity.getRole().getAuthority())
        .fingerprint(userEntity.getFingerprint())
        .build();

    String accessToken = jwtTokenService.generateToken(tokenRequest);
    String refreshToken = jwtTokenService.generateRefreshToken(
        userEntity.getUserId(),
        userEntity.getFingerprint()
    );

    return Pair.of(accessToken, refreshToken);
  }

}
