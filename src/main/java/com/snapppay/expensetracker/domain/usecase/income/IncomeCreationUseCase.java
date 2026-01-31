package com.snapppay.expensetracker.domain.usecase.income;

import com.snapppay.expensetracker.domain.error.Error.BadRequestException;
import com.snapppay.expensetracker.domain.error.ErrorEnum;
import com.snapppay.expensetracker.domain.model.income.Income;
import com.snapppay.expensetracker.domain.model.income.IncomeInfo;
import com.snapppay.expensetracker.domain.service.income.IncomeCreationService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomeCreationUseCase {

  private final IncomeCreationService incomeCreationService;

  public Income create(IncomeInfo info) {

    checkInputValidity(info.source(), info.amount());

    return incomeCreationService.create(info);
  }


  private void checkInputValidity(String source, BigDecimal amount) {
    if (source == null) {
      throw new BadRequestException(ErrorEnum.REQUIRED_FIELD);
    }

    if (amount == null) {
      throw new BadRequestException(ErrorEnum.REQUIRED_FIELD);
    }
  }
}
