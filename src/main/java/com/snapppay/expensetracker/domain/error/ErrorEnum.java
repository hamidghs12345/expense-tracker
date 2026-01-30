package com.snapppay.expensetracker.domain.error;

import lombok.Getter;

@Getter
public enum ErrorEnum {

    GENERAL_INTERNAL_SERVER_ERROR(500101, 500, "error_general_internal_server_error"),

    GENERAL_BAD_REQUEST(400101, 400, "error_general_bad_request"),
    MOBILE_REQUIRED(400104, 400, "error_mobile_required"),

    UNAUTHORIZED(401101, 401, "error_unauthorized"),

    GENERAL_NOT_FOUND(404101, 404, "error_general_not_found"),

    GENERAL_ACCESS_DENIED(403101, 403, "error_general_access_denied"),

    GENERAL_DUPLICATION(409101, 409, "error_general_duplication"),
    OTP_DUPLICATION(409102, 409, "error_otp_duplication");

    private int code;
    private int status;
    private String messageKey;

    ErrorEnum(int code, int status, String messageKey) {
        this.code = code;
        this.status = status;
        this.messageKey = messageKey;
    }

}
