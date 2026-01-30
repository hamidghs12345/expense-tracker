package com.snapppay.expensetracker.domain.usecase;

import com.snapppay.expensetracker.domain.error.Error.BadRequestException;
import com.snapppay.expensetracker.domain.error.ErrorEnum;
import com.snapppay.expensetracker.domain.model.OtpReferenceType;
import com.snapppay.expensetracker.domain.service.OtpCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationCodeUseCase {

  private final OtpCreationService otpCreationService;

  public String generateAndSentOtpCode(String mobile) {
    if (mobile == null) {
      throw new BadRequestException(ErrorEnum.MOBILE_REQUIRED);
    }

    return otpCreationService.generateOtp(mobile, OtpReferenceType.REGISTRATION);

  }

}
