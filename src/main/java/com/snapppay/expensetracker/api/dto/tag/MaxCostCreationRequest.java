package com.snapppay.expensetracker.api.dto.tag;

import java.util.List;
import java.util.UUID;

public record MaxCostCreationRequest(
    UUID tagId,
    List<MaxCostReq> maxCostReqs) {
}

