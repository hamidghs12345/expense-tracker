package com.snapppay.expensetracker.domain.service.tag;

import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.MaxCostEntity.Period;
import java.math.BigDecimal;
import java.util.UUID;

public interface MaxCostCalculationService {

  BigDecimal calculateTotalForPeriod(UUID tagId, Period period);
}
