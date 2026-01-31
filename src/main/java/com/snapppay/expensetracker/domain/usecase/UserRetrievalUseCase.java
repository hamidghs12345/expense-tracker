package com.snapppay.expensetracker.domain.usecase;

import com.snapppay.expensetracker.domain.error.Error.UnauthorizedException;
import com.snapppay.expensetracker.domain.model.User;
import com.snapppay.expensetracker.domain.service.UserRetrievalService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRetrievalUseCase {

  private final UserRetrievalService userRetrievalService;


  public User getCurrentUser(Long userId) {
    checkNullableUserId(userId);
    return fetchUser(userId);

  }

  private static void checkNullableUserId(Long userId) {
    if (Objects.isNull(userId)) {
      throw new UnauthorizedException();
    }
  }

  private User fetchUser(Long userId) {
    return userRetrievalService.findUserById(userId);
  }

}