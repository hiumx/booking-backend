package com.hiumx.bookingbackend.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserResetPasswordRequest {
    private String currentPassword;

    @Size(min = 8, message = "PASSWORD_INVALID")
    private String newPassword;

    private String newPasswordConfirm;
}
