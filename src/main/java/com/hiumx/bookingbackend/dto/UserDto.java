package com.hiumx.bookingbackend.dto;

import com.hiumx.bookingbackend.entity.Gender;
import com.hiumx.bookingbackend.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {
    private Long id;
    private String email;
    private String phone;
    private String name;

//    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
    private LocalDate dob;
    private Gender gender;
    private String address;
    private String image;
    private Role role;
    private Integer isActive;
}
