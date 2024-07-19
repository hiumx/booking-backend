package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.HistorySearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistorySearchRepository extends JpaRepository<HistorySearch, Long> {
    List<HistorySearch> findByUserId(Long userId);

    List<HistorySearch> findByHotelId(Long hotelId);

    @Query(value = "SELECT * FROM history_searches WHERE user_id= ?1 ORDER BY id DESC LIMIT 4 ", nativeQuery = true)
    List<HistorySearch> findTop4Recent(Long userId);
}
