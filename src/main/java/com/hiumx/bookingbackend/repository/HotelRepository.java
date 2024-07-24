package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.HistorySearch;
import com.hiumx.bookingbackend.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query("SELECT h.location, COUNT(h) FROM Hotel h GROUP BY h.location")
    List<Object[]> countHotelsByLocation();
}
