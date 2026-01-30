package com.snapppay.expensetracker.domain.service;


import com.snapppay.expensetracker.domain.model.OtpReferenceType;

public interface OtpValidationService {

    boolean isCodeValid(String reference, OtpReferenceType type, String code);

}
