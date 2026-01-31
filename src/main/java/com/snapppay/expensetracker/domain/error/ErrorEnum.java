package com.snapppay.expensetracker.domain.error;

import lombok.Getter;

@Getter
public enum ErrorEnum {

    GENERAL_INTERNAL_SERVER_ERROR(500101, 500, "error_general_internal_server_error"),

    GENERAL_BAD_REQUEST(400101, 400, "error_general_bad_request"),
    MOBILE_REQUIRED(400102, 400, "error_mobile_required"),
    OTP_INVALID(400103, 400, "error_otp_invalid"),
    LOGIN_INFO_REQUIRED(400104, 400, "error_login_info_required"),
    EXCEED_MAX_PASSWORD_LENGTH(400105, 400, "error_exceed_max_password_length"),
    REFRESH_TOKEN_REQUIRED(400106, 400, "error_refresh_token_required"),
    REQUIRED_FIELD(400107, 400, "error_required_field"),


    UNAUTHORIZED(401101, 401, "error_unauthorized"),

    GENERAL_NOT_FOUND(404101, 404, "error_general_not_found"),
    USER_ROLE_NOT_FOUND(404102, 404, "error_user_role_not_found"),

    GENERAL_ACCESS_DENIED(403101, 403, "error_general_access_denied"),

    GENERAL_DUPLICATION(409101, 409, "error_general_duplication"),
    OTP_DUPLICATION(409102, 409, "error_otp_duplication"),
    USER_DUPLICATION(409103, 409, "error_user_duplication");

    private int code;
    private int status;
    private String messageKey;

    ErrorEnum(int code, int status, String messageKey) {
        this.code = code;
        this.status = status;
        this.messageKey = messageKey;
    }

}
