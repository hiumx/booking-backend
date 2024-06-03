package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.ReviewRequest;
import com.hiumx.bookingbackend.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewService {
    ReviewResponse create(ReviewRequest request);

    List<ReviewResponse> getAll();
}
