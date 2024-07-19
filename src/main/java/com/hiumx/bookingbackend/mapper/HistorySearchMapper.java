package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.document.BookingDocument;
import com.hiumx.bookingbackend.dto.request.BookingRequest;
import com.hiumx.bookingbackend.dto.request.HistorySearchRequest;
import com.hiumx.bookingbackend.dto.response.BookingGetResponse;
import com.hiumx.bookingbackend.dto.response.BookingResponse;
import com.hiumx.bookingbackend.dto.response.HistorySearchResponse;
import com.hiumx.bookingbackend.dto.response.HotelResponse;
import com.hiumx.bookingbackend.entity.Booking;
import com.hiumx.bookingbackend.entity.HistorySearch;
import com.hiumx.bookingbackend.entity.Room;

import java.util.HashSet;

public class HistorySearchMapper {

    public static HistorySearch toHistorySearch(HistorySearchRequest request) {
        return HistorySearch.builder()
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .adult(request.getAdult())
                .children(request.getChildren())
                .rooms(request.getRooms())
                .build();
    }

    public static HistorySearchResponse toHistorySearchResponse(HistorySearch historySearch) {
        return HistorySearchResponse.builder()
                .userId(historySearch.getUser().getId())
                .startDate(historySearch.getStartDate())
                .endDate(historySearch.getEndDate())
                .adult(historySearch.getAdult())
                .children(historySearch.getChildren())
                .rooms(historySearch.getRooms())
                .hotel(HotelMapper.toHotelResponse(historySearch.getHotel()))
                .build();
    }
}
