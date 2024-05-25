package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
