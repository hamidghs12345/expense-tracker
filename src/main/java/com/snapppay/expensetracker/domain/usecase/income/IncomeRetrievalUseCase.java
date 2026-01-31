package com.snapppay.expensetracker.domain.usecase.income;

import com.snapppay.expensetracker.domain.model.income.Income;
import com.snapppay.expensetracker.domain.model.income.IncomeFilter;
import com.snapppay.expensetracker.domain.service.income.IncomeRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomeRetrievalUseCase {

  private final IncomeRetrievalService incomeRetrievalService;

  public Page<Income> getAll(IncomeFilter incomeFilter) {
    return incomeRetrievalService.getAll(incomeFilter);
  }
}