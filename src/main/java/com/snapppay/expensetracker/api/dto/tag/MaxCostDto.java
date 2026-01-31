package com.snapppay.expensetracker.api.dto.tag;

import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.MaxCostEntity.Period;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaxCostDto {

  private UUID id;
  private String amount;
  private Period period;
}
