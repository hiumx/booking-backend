package com.hiumx.bookingbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReviewResponse {
    private Long id;
    private String title;
    private String content;
    private UserReviewResponse user;
    private Long hotelId;
    private Float point;
}
