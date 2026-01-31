package com.snapppay.expensetracker.api.controller;

import com.snapppay.expensetracker.api.dto.UserDto;
import com.snapppay.expensetracker.api.dto.mapper.UserMapper;
import com.snapppay.expensetracker.domain.model.User;
import com.snapppay.expensetracker.domain.usecase.UserRetrievalUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserRetrievalController extends AbstractController {

  private final UserRetrievalUseCase userRetrievalUseCase;
  private final UserMapper userMapper;

  @GetMapping("/me")
  public ResponseEntity<UserDto> getCurrentUser() {
    User user = userRetrievalUseCase.getCurrentUser(
        getCurrentUserId());

    return ResponseEntity.ok(userMapper.toDto(user));
  }

}
