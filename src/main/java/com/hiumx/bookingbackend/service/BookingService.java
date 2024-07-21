package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.BookingRequest;
import com.hiumx.bookingbackend.dto.response.BookingGetResponse;
import com.hiumx.bookingbackend.dto.response.BookingGetWithUserResponse;
import com.hiumx.bookingbackend.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse create(BookingRequest request);
    List<BookingGetResponse> getBookingByUserId(Long id);
    List<BookingGetWithUserResponse> getBookingByHotelId(Long id);
    BookingResponse deleteBookingById(Long id);
}
