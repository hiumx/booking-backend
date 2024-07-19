package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.HistorySearch;
import com.hiumx.bookingbackend.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
