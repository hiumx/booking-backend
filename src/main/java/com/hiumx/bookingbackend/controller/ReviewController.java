package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.ReviewRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.ReviewResponse;
import com.hiumx.bookingbackend.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin(value = "http://localhost:3000")
@AllArgsConstructor
public class ReviewController {

    private ReviewService reviewService;

    @PostMapping
    public ApiResponse<?> create(@RequestBody ReviewRequest request) {
        ReviewResponse reviewResponse = reviewService.create(request);
        return ApiResponse.builder()
                .code(1000)
                .message("Create review successfully")
                .metadata(reviewResponse)
                .build();
    }

    @GetMapping
    public ApiResponse<?> getAll() {
        List<ReviewResponse> reviewsResponse = reviewService.getAll();
        return ApiResponse.builder()
                .code(1000)
                .message("Get review successfully")
                .metadata(reviewsResponse)
                .build();
    }
}
