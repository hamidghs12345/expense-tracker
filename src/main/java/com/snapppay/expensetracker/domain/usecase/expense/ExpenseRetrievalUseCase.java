package com.snapppay.expensetracker.domain.usecase.expense;

import com.snapppay.expensetracker.domain.model.expense.Expense;
import com.snapppay.expensetracker.domain.model.expense.ExpenseFilter;
import com.snapppay.expensetracker.domain.service.expense.ExpenseRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseRetrievalUseCase {

  private final ExpenseRetrievalService expenseRetrievalService;

  public Page<Expense> getAll(ExpenseFilter expenseFilter) {
    return expenseRetrievalService.getAll(expenseFilter);
  }
}