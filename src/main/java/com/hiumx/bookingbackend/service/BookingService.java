package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.BookingRequest;
import com.hiumx.bookingbackend.dto.response.BookingResponse;

public interface BookingService {
    BookingResponse create(BookingRequest request);
}
