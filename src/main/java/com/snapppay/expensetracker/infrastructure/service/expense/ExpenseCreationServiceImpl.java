package com.snapppay.expensetracker.infrastructure.service.expense;

import com.snapppay.expensetracker.domain.model.expense.Expense;
import com.snapppay.expensetracker.domain.model.expense.ExpenseInfo;
import com.snapppay.expensetracker.domain.service.expense.ExpenseCreationService;
import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.ExpenseEntity;
import com.snapppay.expensetracker.infrastructure.persistence.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseCreationServiceImpl implements ExpenseCreationService {

  private final ModelMapper modelMapper;
  private final ExpenseRepository expenseRepository;


  @Override
  public Expense create(ExpenseInfo expenseInfo) {

    ExpenseEntity expenseEntity = ExpenseEntity.builder()
        .tagId(expenseInfo.tagId())
        .amount(expenseInfo.amount())
        .build();

    expenseRepository.save(expenseEntity);

    return modelMapper.map(expenseEntity, Expense.class);

  }
}
