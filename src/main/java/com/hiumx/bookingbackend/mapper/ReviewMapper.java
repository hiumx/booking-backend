package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.document.ReviewDocument;
import com.hiumx.bookingbackend.dto.request.ReviewRequest;
import com.hiumx.bookingbackend.dto.response.ReviewGetAllHotelResponse;
import com.hiumx.bookingbackend.dto.response.ReviewResponse;
import com.hiumx.bookingbackend.entity.Review;

public class ReviewMapper {
    public static Review toReview(ReviewRequest request) {
        return Review.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .point(request.getPoint())
                .build();
    }

    public static ReviewResponse toReviewResponse(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .hotelId(review.getId())
                .point(review.getPoint())
                .build();
    }

    public static ReviewResponse toReviewResponse(ReviewDocument review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .point(review.getPoint())
                .build();
    }

    public static ReviewGetAllHotelResponse toReviewGetAllHotelResponse(Review review) {
        return ReviewGetAllHotelResponse.builder()
                .point(review.getPoint())
                .build();
    }

    public static ReviewGetAllHotelResponse toReviewGetAllHotelResponse(ReviewDocument review) {
        return ReviewGetAllHotelResponse.builder()
                .point(review.getPoint())
                .build();
    }

    public static ReviewDocument toReviewDocument(Review review) {
        return ReviewDocument.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .userId(review.getUser().getId())
                .hotelId(review.getHotel().getId())
                .point(review.getPoint())
                .build();
    }
}
