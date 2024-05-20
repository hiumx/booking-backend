package com.hiumx.bookingbackend.exception;

import com.hiumx.bookingbackend.enums.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException extends RuntimeException{
    private ErrorCode errorCode;

    public ApplicationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode=errorCode;
    }
}
