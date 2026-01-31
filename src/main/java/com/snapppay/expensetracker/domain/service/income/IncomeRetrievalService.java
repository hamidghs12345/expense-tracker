package com.snapppay.expensetracker.domain.service.income;

import com.snapppay.expensetracker.domain.model.income.Income;
import com.snapppay.expensetracker.domain.model.income.IncomeFilter;
import org.springframework.data.domain.Page;

public interface IncomeRetrievalService {

  Page<Income> getAll(IncomeFilter incomeFilter);

}
