package com.snapppay.expensetracker.api.dto.income.mapper;

import com.snapppay.expensetracker.api.dto.income.IncomeDto;
import com.snapppay.expensetracker.domain.model.income.Income;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class IncomeMapper {

  public IncomeDto toDto(Income income) {
    return IncomeDto.builder()
        .id(income.getId())
        .amount(toString(income.getAmount()))
        .source(income.getSource())
        .createdAt(income.getCreatedAt())
        .updatedAt(income.getUpdatedAt())
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
