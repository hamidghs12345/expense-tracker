package com.snapppay.expensetracker.domain.model.income;


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
public class Income {

  private UUID id;
  private BigDecimal amount;
  private Long userId;
  private String source;
  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;



}
