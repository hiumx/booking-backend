package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.Hotel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
//    @Query("SELECT h FROM Hotel h JOIN FETCH h.images WHERE h.id = :hotelId")
//    Hotel findByIdWithImages(@Param("hotelId") Long hotelId);
}
