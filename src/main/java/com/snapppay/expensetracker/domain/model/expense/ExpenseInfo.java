package com.snapppay.expensetracker.domain.model.expense;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ExpenseInfo(
    BigDecimal amount,
    UUID tagId
) {

}