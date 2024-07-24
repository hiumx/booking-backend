package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.Review;
import com.hiumx.bookingbackend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByHotelId(Long hotelId);
    @Query("SELECT r FROM Review r WHERE r.hotel.id = ?1 AND r.user.id = ?2")
    Review findReviewsByHotelIdAndUserId(Long hotelId, Long userId);

}
