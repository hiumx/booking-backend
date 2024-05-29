package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.ReviewRequest;
import com.hiumx.bookingbackend.dto.response.ReviewResponse;

public interface ReviewService {
    ReviewResponse create(ReviewRequest request);
}
