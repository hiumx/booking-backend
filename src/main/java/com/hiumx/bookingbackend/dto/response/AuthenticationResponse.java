package com.hiumx.bookingbackend.dto.response;

import com.hiumx.bookingbackend.entity.Gender;
import com.hiumx.bookingbackend.entity.Role;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AuthenticationResponse {
    private Long id;
    private String email;
    private String phone;
    private String name;
    private LocalDate dob;
    private Gender gender;
    private String address;
    private Role role;
    private String image;
    private String token;
}
