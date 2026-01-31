package com.snapppay.expensetracker.domain.model.tag;

import lombok.Builder;

@Builder
public record TagInfo(
    Long userId,
    String name
) {

}