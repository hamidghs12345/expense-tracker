package com.snapppay.expensetracker.domain.model.income;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record IncomeInfo(
    Long userId,
    BigDecimal amount,
    String source
) {

}