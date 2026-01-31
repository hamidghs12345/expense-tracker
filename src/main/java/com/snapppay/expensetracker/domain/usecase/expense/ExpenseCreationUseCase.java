package com.snapppay.expensetracker.domain.usecase.expense;

import com.snapppay.expensetracker.domain.error.Error.BadRequestException;
import com.snapppay.expensetracker.domain.error.ErrorEnum;
import com.snapppay.expensetracker.domain.model.expense.Expense;
import com.snapppay.expensetracker.domain.model.expense.ExpenseInfo;
import com.snapppay.expensetracker.domain.service.expense.ExpenseCreationService;
import com.snapppay.expensetracker.domain.service.tag.TagRetrievalService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseCreationUseCase {

  private final ExpenseCreationService expenseCreationService;
  private final TagRetrievalService tagRetrievalService;

  public Expense create(ExpenseInfo expenseInfo) {

    checkInputValidity(expenseInfo.amount());

    tagRetrievalService.get(expenseInfo.tagId());

    return expenseCreationService.create(expenseInfo);
  }

  private void checkInputValidity(BigDecimal amount) {
    if (amount == null) {
      throw new BadRequestException(ErrorEnum.REQUIRED_FIELD);
    }
  }
}
