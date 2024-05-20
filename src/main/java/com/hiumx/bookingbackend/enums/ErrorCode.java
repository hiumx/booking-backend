package com.hiumx.bookingbackend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001, "User already existed!", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(1002, "Role not found!", HttpStatus.NOT_FOUND),
    PASSWORD_INVALID(1003, "Password must be at least 8 characters!", HttpStatus.BAD_REQUEST),
    KEY_INVALID(1004, "Key message invalid!", HttpStatus.BAD_REQUEST),
    AUTHENTICATION_ERROR(1005, "Email or phone number invalid!", HttpStatus.UNAUTHORIZED),
    PASSWORD_INCORRECT(1006, "Incorrect password!", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1007, "User not found!", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(1008, "Permission denied!", HttpStatus.FORBIDDEN),
    UNAUTHENTICATED(1009, "Unauthenticated!", HttpStatus.UNAUTHORIZED)
    ;

    private int code;
    private String message;
    private HttpStatus httpStatus;
}
