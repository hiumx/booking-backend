package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.Review;
import com.hiumx.bookingbackend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByHotelId(Long hotelId);

}
