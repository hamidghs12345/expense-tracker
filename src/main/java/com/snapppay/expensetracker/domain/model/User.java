package com.snapppay.expensetracker.domain.model;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private Long id;
  private UUID userId;

  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;

  private UserRole role;
  private String mobile;
  private String password;
  private String fingerprint;

}
