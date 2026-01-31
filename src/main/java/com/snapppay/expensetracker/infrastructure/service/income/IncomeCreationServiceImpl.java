package com.snapppay.expensetracker.infrastructure.service.income;

import com.snapppay.expensetracker.domain.error.Error.UnauthorizedException;
import com.snapppay.expensetracker.domain.model.income.Income;
import com.snapppay.expensetracker.domain.model.income.IncomeInfo;
import com.snapppay.expensetracker.domain.service.income.IncomeCreationService;
import com.snapppay.expensetracker.infrastructure.persistence.entity.UserEntity;
import com.snapppay.expensetracker.infrastructure.persistence.entity.income.IncomeEntity;
import com.snapppay.expensetracker.infrastructure.persistence.repository.IncomeRepository;
import com.snapppay.expensetracker.infrastructure.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomeCreationServiceImpl implements IncomeCreationService {

  private final ModelMapper modelMapper;
  private final UserRepository userRepository;
  private final IncomeRepository incomeRepository;

  @Override
  public Income create(IncomeInfo info) {

    UserEntity userEntity = userRepository.findById(info.userId())
        .orElseThrow(UnauthorizedException::new);

    IncomeEntity incomeEntity = IncomeEntity.builder()
        .source(info.source())
        .amount(info.amount())
        .userId(userEntity.getId())
        .build();

    incomeRepository.save(incomeEntity);

    return modelMapper.map(incomeEntity, Income.class);
  }

}
