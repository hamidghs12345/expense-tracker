package com.snapppay.expensetracker.domain.model.tag;


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
public class Tag {

  private UUID id;
  private String name;
  private Long userId;
  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;

}
