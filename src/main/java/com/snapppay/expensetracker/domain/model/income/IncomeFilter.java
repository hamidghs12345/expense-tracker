package com.snapppay.expensetracker.domain.model.income;

import java.time.ZonedDateTime;
import lombok.Builder;

@Builder
public record IncomeFilter(
    Long userId,
    String source,
    ZonedDateTime fromDate,
    ZonedDateTime toDate,
    int page,
    int size
) {

}
