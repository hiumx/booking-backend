package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.Hotel;
import com.hiumx.bookingbackend.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByHotelId(Long hotelId);
}
