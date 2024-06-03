package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.BookingRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.BookingResponse;
import com.hiumx.bookingbackend.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/api/v1/bookings")
@AllArgsConstructor
public class BookingController {

    private BookingService bookingService;

    @PostMapping
    public ApiResponse<?> create(@RequestBody BookingRequest request) {
        BookingResponse bookingResponse = bookingService.create(request);
        return ApiResponse.builder()
                .code(1000)
                .message("Create new booking successfully")
                .metadata(bookingResponse)
                .build();
    }
}
