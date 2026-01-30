package com.snapppay.expensetracker.infrastructure.persistence.repository;

import com.snapppay.expensetracker.domain.model.OtpReferenceType;
import com.snapppay.expensetracker.infrastructure.persistence.entity.OtpEntity;
import com.snapppay.expensetracker.infrastructure.persistence.entity.OtpId;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, OtpId> {

  Optional<OtpEntity> findByReferenceAndReferenceType(String reference, OtpReferenceType type);

  Optional<OtpEntity> findByReferenceAndReferenceTypeAndCodeAndExpirationDateAfter(
      String reference,
      OtpReferenceType type,
      String code,
      Date date
  );

  default Optional<OtpEntity> getNotExpiredOtp(String reference, OtpReferenceType type, String code) {
    return findByReferenceAndReferenceTypeAndCodeAndExpirationDateAfter(
        reference,
        type,
        code,
        new Date(System.currentTimeMillis())
    );
  }
}
