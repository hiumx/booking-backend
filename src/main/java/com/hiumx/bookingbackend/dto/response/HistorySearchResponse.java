package com.hiumx.bookingbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HistorySearchResponse {
    private Long userId;
    private HotelResponse hotel;
    private LocalDate startDate;
    private LocalDate endDate;
    private int adult;
    private int children;
    private int rooms;
}
