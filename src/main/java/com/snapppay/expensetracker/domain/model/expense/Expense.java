package com.snapppay.expensetracker.domain.model.expense;


import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

  private UUID id;
  private BigDecimal amount;
  private UUID tagId;
  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;

}
