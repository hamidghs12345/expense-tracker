package com.snapppay.expensetracker.api.dto.expense;

import java.math.BigDecimal;
import java.util.UUID;

public record ExpenseCreationRequest(
    BigDecimal amount,
    UUID tagId) {
}
