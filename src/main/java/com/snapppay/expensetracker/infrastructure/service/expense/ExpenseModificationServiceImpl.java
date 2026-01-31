package com.snapppay.expensetracker.infrastructure.service.expense;

import com.snapppay.expensetracker.domain.error.Error.NotFoundException;
import com.snapppay.expensetracker.domain.service.expense.ExpenseModificationService;
import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.ExpenseEntity;
import com.snapppay.expensetracker.infrastructure.persistence.repository.ExpenseRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExpenseModificationServiceImpl implements ExpenseModificationService {

  private final ExpenseRepository expenseRepository;

  @Override
  @Transactional
  public void delete(UUID id) {

    ExpenseEntity expenseEntity = expenseRepository.findById(
            id)
        .orElseThrow(NotFoundException::new);

    expenseRepository.delete(expenseEntity);
  }

}
