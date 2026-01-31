package com.snapppay.expensetracker.api.dto.mapper;

import com.snapppay.expensetracker.api.dto.UserDto;
import com.snapppay.expensetracker.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserDto toDto(User user) {
    return UserDto.builder()
        .userId(user.getUserId())
        .role(user.getRole())
        .createdAt(user.getCreatedAt())
        .updatedAt(user.getUpdatedAt())
        .mobile(user.getMobile())
        .build();
  }
}
