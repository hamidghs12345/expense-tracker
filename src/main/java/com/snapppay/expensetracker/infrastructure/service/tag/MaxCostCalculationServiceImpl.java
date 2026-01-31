package com.snapppay.expensetracker.infrastructure.service.tag;

import com.snapppay.expensetracker.domain.service.tag.MaxCostCalculationService;
import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.MaxCostEntity.Period;
import com.snapppay.expensetracker.infrastructure.persistence.repository.ExpenseRepository;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaxCostCalculationServiceImpl implements MaxCostCalculationService {

  private final ExpenseRepository expenseRepository;

  @Override
  public BigDecimal calculateTotalForPeriod(UUID tagId, Period period) {

    ZonedDateTime now = ZonedDateTime.now();

    ZonedDateTime from = switch (period) {
      case DAILY -> now.toLocalDate().atStartOfDay(now.getZone());

      case WEEKLY -> now.with(DayOfWeek.MONDAY)
          .toLocalDate().atStartOfDay(now.getZone());

      case MONTHLY -> now.withDayOfMonth(1).toLocalDate().atStartOfDay(now.getZone());

      case YEARLY -> now.withDayOfYear(1).toLocalDate().atStartOfDay(now.getZone());
    };

    return expenseRepository
        .sumAmountByTagIdAndCreatedAtAfter(tagId, from);

  }
}
