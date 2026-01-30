package com.snapppay.expensetracker.domain.service;


import com.snapppay.expensetracker.domain.model.OtpReferenceType;

public interface OtpCreationService {

  String generateOtp(String reference, OtpReferenceType type);

}
