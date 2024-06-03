package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
