package com.snapppay.expensetracker.infrastructure.service;

import com.snapppay.expensetracker.domain.error.Error.DuplicateException;
import com.snapppay.expensetracker.domain.error.ErrorEnum;
import com.snapppay.expensetracker.domain.model.OtpReferenceType;
import com.snapppay.expensetracker.domain.service.OtpCreationService;
import com.snapppay.expensetracker.infrastructure.persistence.entity.OtpEntity;
import com.snapppay.expensetracker.infrastructure.persistence.repository.OtpRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpCreationServiceImpl implements OtpCreationService {

    private final OtpRepository otpRepository;

    @Value("${otp.validity_duration_in_minutes}")
    private int otpValidityDurationInMinutes;

    private final Random random;

    @Override
    public String generateOtp(String reference, OtpReferenceType type) {

        OtpEntity otp = otpRepository.findByReferenceAndReferenceType(reference, type)
            .orElse(OtpEntity.builder()
                .reference(reference)
                .referenceType(type)
                .build());

        Date nowDate = new Date(System.currentTimeMillis());

        if (otp.getExpirationDate() != null && otp.getExpirationDate().after(nowDate)) {
            throw new DuplicateException(ErrorEnum.OTP_DUPLICATION);
        }

        String code = String.valueOf(generateRandomCode());
        otp.setCode(code);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MINUTE, otpValidityDurationInMinutes);

        otp.setExpirationDate(calendar.getTime());

        otpRepository.save(otp);

        return code;
    }

    private int generateRandomCode() {
        return  10000 + random.nextInt(90000);
    }

}
