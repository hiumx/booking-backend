package com.hiumx.bookingbackend.dto.response;

import com.hiumx.bookingbackend.entity.Gender;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserCreationResponse {
    private Long id;
    private String email;
    private String phone;
    private String name;
    private LocalDate dob;
    private Gender gender;
    private String address;
    private String image;
    private Set<RoleResponse> roles;
    private Integer isActive;
}
