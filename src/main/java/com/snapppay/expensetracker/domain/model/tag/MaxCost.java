package com.snapppay.expensetracker.domain.model.tag;


import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.MaxCostEntity.Period;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaxCost {

  private UUID id;
  private BigDecimal amount;
  private Period period;
  private UUID tagId;

}
