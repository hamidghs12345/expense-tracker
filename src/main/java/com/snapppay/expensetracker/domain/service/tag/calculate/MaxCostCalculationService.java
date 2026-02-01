package com.snapppay.expensetracker.domain.service.tag.calculate;

import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.MaxCostEntity.Period;
import java.math.BigDecimal;
import java.util.UUID;

public interface MaxCostCalculationService {

  BigDecimal calculateByTagIdForPeriod(UUID tagId, Period period);
}
