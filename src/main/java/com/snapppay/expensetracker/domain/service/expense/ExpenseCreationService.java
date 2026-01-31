package com.snapppay.expensetracker.domain.service.expense;

import com.snapppay.expensetracker.domain.model.expense.Expense;
import com.snapppay.expensetracker.domain.model.expense.ExpenseInfo;

public interface ExpenseCreationService {

  Expense create(ExpenseInfo expenseInfo);
}
