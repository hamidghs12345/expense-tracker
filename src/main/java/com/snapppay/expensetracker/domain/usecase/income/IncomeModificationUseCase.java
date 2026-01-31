package com.snapppay.expensetracker.domain.usecase.income;

import com.snapppay.expensetracker.domain.error.Error.NotFoundException;
import com.snapppay.expensetracker.domain.model.User;
import com.snapppay.expensetracker.domain.service.UserRetrievalService;
import com.snapppay.expensetracker.domain.service.income.IncomeModificationService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomeModificationUseCase {

  private final UserRetrievalService userRetrievalService;
  private final IncomeModificationService incomeModificationService;

  public void delete(UUID id, Long userId) {

    User user = userRetrievalService.findUserById(userId);

    if (user == null) {
      throw new NotFoundException();
    }

    incomeModificationService.delete(id);
  }
}
