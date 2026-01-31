package com.snapppay.expensetracker.infrastructure.service;


import com.snapppay.expensetracker.domain.model.User;
import com.snapppay.expensetracker.domain.service.UserRetrievalService;
import com.snapppay.expensetracker.domain.usecase.error.UserNotFoundException;
import com.snapppay.expensetracker.infrastructure.persistence.entity.UserEntity;
import com.snapppay.expensetracker.infrastructure.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRetrievalServiceImpl implements UserRetrievalService {

  private final UserRepository userRepository;
  private final ModelMapper modelMapper;

  @Override
  public User findUserById(Long userId) {
    return userRepository.findById(userId)
        .map(this::mapToDomain)
        .orElseThrow(UserNotFoundException::new);
  }

  private User mapToDomain(UserEntity userEntity) {
    return modelMapper.map(userEntity, User.class);
  }
}
