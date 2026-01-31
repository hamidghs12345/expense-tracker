package com.snapppay.expensetracker.api.dto.income;

import java.math.BigDecimal;

public record IncomeCreationRequest(

    String source,
    BigDecimal amount
) {

}
