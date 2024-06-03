package com.hiumx.bookingbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReviewRequest {
    private String title;
    private String content;
    private Long userId;
    private Long hotelId;
    private Float point;
}
