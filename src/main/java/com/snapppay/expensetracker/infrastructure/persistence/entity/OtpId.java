package com.snapppay.expensetracker.infrastructure.persistence.entity;

import com.snapppay.expensetracker.domain.model.OtpReferenceType;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OtpId implements Serializable {

  private String reference;
  private OtpReferenceType referenceType;

}
