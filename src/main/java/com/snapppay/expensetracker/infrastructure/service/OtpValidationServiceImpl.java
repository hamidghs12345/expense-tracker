package com.snapppay.expensetracker.infrastructure.service;

import com.snapppay.expensetracker.domain.model.OtpReferenceType;
import com.snapppay.expensetracker.domain.service.OtpValidationService;
import com.snapppay.expensetracker.infrastructure.persistence.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpValidationServiceImpl implements OtpValidationService {

  private final OtpRepository otpRepository;

  @Override
  public boolean isCodeValid(String reference, OtpReferenceType type, String code) {
    return otpRepository.getNotExpiredOtp(reference, type, code)
        .isPresent();
  }

}
