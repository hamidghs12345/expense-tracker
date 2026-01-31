package com.snapppay.expensetracker.domain.service.expense;

import com.snapppay.expensetracker.domain.model.expense.Expense;
import com.snapppay.expensetracker.domain.model.expense.ExpenseFilter;
import org.springframework.data.domain.Page;

public interface ExpenseRetrievalService {

  Page<Expense> getAll(ExpenseFilter expenseFilter);
}
