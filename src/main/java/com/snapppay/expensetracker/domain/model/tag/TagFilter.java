package com.snapppay.expensetracker.domain.model.tag;

import java.time.ZonedDateTime;
import lombok.Builder;

@Builder
public record TagFilter(
    Long userId,
    String name,
    ZonedDateTime fromDate,
    ZonedDateTime toDate,
    int page,
    int size
) {

}
