package com.snapppay.expensetracker.api.dto.tag.mapper;

import com.snapppay.expensetracker.api.dto.tag.MaxCostDto;
import com.snapppay.expensetracker.domain.model.tag.MaxCost;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MaxCostMapper {

  public MaxCostDto toDto(MaxCost maxCost) {
    return MaxCostDto.builder()
        .id(maxCost.getId())
        .amount(toString(maxCost.getAmount()))
        .period(maxCost.getPeriod())
        .build();
  }


  public List<MaxCostDto> toDtos(List<MaxCost> maxCosts) {

    if (maxCosts == null || maxCosts.isEmpty()) {
      return List.of();
    }

    return maxCosts
        .stream()
        .map(this::toDto)
        .toList();
  }

  private String toString(BigDecimal amount) {
    if (amount == null) {
      return null;
    }

    return amount.stripTrailingZeros()
        .toPlainString();
  }
}
