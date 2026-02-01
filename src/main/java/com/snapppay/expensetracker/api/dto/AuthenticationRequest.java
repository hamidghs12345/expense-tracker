package com.snapppay.expensetracker.api.dto;

public record AuthenticationRequest(
    String mobile,
    String password
) {

}
