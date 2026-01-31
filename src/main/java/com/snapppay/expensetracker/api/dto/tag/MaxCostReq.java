package com.snapppay.expensetracker.api.dto.tag;

import com.snapppay.expensetracker.infrastructure.persistence.entity.tag.MaxCostEntity.Period;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaxCostReq {

  @NotNull private Period period;
  @NotNull private BigDecimal amount;

}
