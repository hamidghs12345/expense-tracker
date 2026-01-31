package com.snapppay.expensetracker.domain.model.expense;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record ExpenseFilter(
    UUID tagId,
    ZonedDateTime fromDate,
    ZonedDateTime toDate,
    int page,
    int size
) {

}
