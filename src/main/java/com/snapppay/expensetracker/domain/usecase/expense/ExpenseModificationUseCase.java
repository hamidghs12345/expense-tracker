package com.snapppay.expensetracker.domain.usecase.expense;

import com.snapppay.expensetracker.domain.error.Error.NotFoundException;
import com.snapppay.expensetracker.domain.model.User;
import com.snapppay.expensetracker.domain.service.UserRetrievalService;
import com.snapppay.expensetracker.domain.service.expense.ExpenseModificationService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseModificationUseCase {

  private final UserRetrievalService userRetrievalService;
  private final ExpenseModificationService expenseModificationService;

  public void delete(UUID id, Long userId) {

    User user = userRetrievalService.findUserById(userId);

    if (user == null) {
      throw new NotFoundException();
    }

    expenseModificationService.delete(id);
  }
}
