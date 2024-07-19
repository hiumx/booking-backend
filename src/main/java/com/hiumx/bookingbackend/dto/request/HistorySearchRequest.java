package com.hiumx.bookingbackend.dto.request;

import com.hiumx.bookingbackend.dto.response.HotelResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HistorySearchRequest {
    private Long userId;
    private Long hotelId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int adult;
    private int children;
    private int rooms;
}
