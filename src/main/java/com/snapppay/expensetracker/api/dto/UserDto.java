package com.snapppay.expensetracker.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.snapppay.expensetracker.domain.model.UserRole;
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
public class UserDto {

  @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  private ZonedDateTime createdAt;
  @JsonFormat(timezone = "UTC", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  private ZonedDateTime updatedAt;

  private UUID userId;
  private UserRole role;
  private String mobile;

}
