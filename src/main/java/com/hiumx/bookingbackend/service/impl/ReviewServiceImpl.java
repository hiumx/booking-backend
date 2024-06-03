package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.document.ReviewDocument;
import com.hiumx.bookingbackend.dto.request.ReviewRequest;
import com.hiumx.bookingbackend.dto.response.ReviewResponse;
import com.hiumx.bookingbackend.entity.Hotel;
import com.hiumx.bookingbackend.entity.Review;
import com.hiumx.bookingbackend.entity.User;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.mapper.ReviewMapper;
import com.hiumx.bookingbackend.mapper.UserMapper;
import com.hiumx.bookingbackend.repository.HotelRepository;
import com.hiumx.bookingbackend.repository.ReviewRepository;
import com.hiumx.bookingbackend.repository.UserRepository;
import com.hiumx.bookingbackend.repository.document.ReviewDocumentRepository;
import com.hiumx.bookingbackend.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private UserRepository userRepository;
    private HotelRepository hotelRepository;
    private ReviewDocumentRepository reviewDocumentRepository;

    @Override
    public ReviewResponse create(ReviewRequest request) {
        Review review = ReviewMapper.toReview(request);

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.HOTEL_NOT_FOUND));

        review.setUser(user);
        review.setHotel(hotel);

        ReviewResponse reviewSaved = ReviewMapper.toReviewResponse(reviewRepository.save(review));
        reviewSaved.setUser(UserMapper.toUserResponse(user));

        return reviewSaved;
    }

    @Override
    public List<ReviewResponse> getAll() {
        List<Review> reviews = reviewRepository.findAll();
//        for (Review r: reviews) {
//            reviewDocumentRepository.save(
//                    ReviewDocument.builder()
//                            .id(r.getId())
//                            .title(r.getTitle())
//                            .content(r.getContent())
//                            .userId(r.getUser().getId())
//                            .hotelId(r.getHotel().getId())
//                            .point(r.getPoint())
//                            .build()
//            );
//        }
        return reviews.stream().map(ReviewMapper::toReviewResponse).toList();
    }
}
