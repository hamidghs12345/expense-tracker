package com.snapppay.expensetracker.infrastructure.service;

import com.snapppay.expensetracker.domain.error.Error.DuplicateException;
import com.snapppay.expensetracker.domain.error.Error.NotFoundException;
import com.snapppay.expensetracker.domain.error.ErrorEnum;
import com.snapppay.expensetracker.domain.model.User;
import com.snapppay.expensetracker.domain.model.UserAuthority;
import com.snapppay.expensetracker.domain.service.UserCreationService;
import com.snapppay.expensetracker.infrastructure.persistence.entity.UserEntity;
import com.snapppay.expensetracker.infrastructure.persistence.entity.UserRoleEntity;
import com.snapppay.expensetracker.infrastructure.persistence.repository.UserRepository;
import com.snapppay.expensetracker.infrastructure.persistence.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreationServiceImpl implements UserCreationService {

  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;
  private final ModelMapper modelMapper;

  @Override
  public User create(String mobile, String password) {
    if (mobile != null && userRepository.existsByMobile(mobile)) {
      throw new DuplicateException(ErrorEnum.USER_DUPLICATION);
    }

    UserRoleEntity roleEntity = userRoleRepository.findById(UserAuthority.getDefault())
        .orElseThrow(() -> new NotFoundException(ErrorEnum.USER_ROLE_NOT_FOUND));

    UserEntity userEntity = UserEntity.builder()
        .role(roleEntity)
        .mobile(mobile)
        .password(password)
        .fingerprint(String.valueOf(System.currentTimeMillis()))
        .build();

    userRepository.save(userEntity);

    return modelMapper.map(userEntity, User.class);
  }
}
