package com.hiumx.bookingbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception"),
    ROLE_NOT_FOUND(1002, "Role not found!"),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters!"),
    KEY_INVALID(1004, "Key message invalid!"),
    AUTHENTICATION_ERROR(1005, "Email or phone number invalid!"),
    PASSWORD_INCORRECT(1006, "Password incorrect!"),
    USER_EXISTED(1001, "User already existed!");

    private int code;
    private String message;
}
