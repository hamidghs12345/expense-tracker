package com.snapppay.expensetracker.api.dto;

public record RegistrationRequest(
    String mobile,
    String password,
    String otpCode
) {
}
