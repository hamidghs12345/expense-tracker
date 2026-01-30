package com.snapppay.expensetracker.infrastructure.persistence.entity;

import com.snapppay.expensetracker.domain.model.OtpReferenceType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "otp")
@IdClass(OtpId.class)
public class OtpEntity {

  @Id
  private String reference;

  @Id
  @Enumerated(EnumType.STRING)
  private OtpReferenceType referenceType;

  private String code;

  private Date expirationDate;

}
