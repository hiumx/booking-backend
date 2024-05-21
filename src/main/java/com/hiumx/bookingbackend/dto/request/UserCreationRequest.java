package com.hiumx.bookingbackend.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserCreationRequest {

    private String email;
    private String phone;
    private String name;

    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
    private LocalDate dob;
    private Long genderId;
    private String address;
    private String image;
    private Set<Long> roleIds;
    private Integer isActive;
}
