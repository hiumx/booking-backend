package com.hiumx.bookingbackend.dto.response;

import com.hiumx.bookingbackend.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserReviewResponse {
    private Long id;
    private String email;
    private String name;
    private String address;
    private String image;
}