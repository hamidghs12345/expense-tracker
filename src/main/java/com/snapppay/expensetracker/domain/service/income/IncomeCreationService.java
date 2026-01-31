package com.snapppay.expensetracker.domain.service.income;

import com.snapppay.expensetracker.domain.model.income.Income;
import com.snapppay.expensetracker.domain.model.income.IncomeInfo;

public interface IncomeCreationService {

  Income create(IncomeInfo incomeInfo);

}
