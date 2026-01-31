package com.snapppay.expensetracker.api.dto.expense.mapper;

import com.snapppay.expensetracker.api.dto.expense.ExpenseDto;
import com.snapppay.expensetracker.domain.model.expense.Expense;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {

  public ExpenseDto toDto(Expense expense) {
    return ExpenseDto.builder()
        .id(expense.getId())
        .amount(toString(expense.getAmount()))
        .createdAt(expense.getCreatedAt())
        .updatedAt(expense.getUpdatedAt())
        .build();
  }



  private String toString(BigDecimal amount) {
    if (amount == null) {
      return null;
    }

    return amount.stripTrailingZeros()
        .toPlainString();
  }
}
