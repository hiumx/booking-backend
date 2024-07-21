package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.BookingRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.BookingGetResponse;
import com.hiumx.bookingbackend.dto.response.BookingGetWithUserResponse;
import com.hiumx.bookingbackend.dto.response.BookingResponse;
import com.hiumx.bookingbackend.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
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

    @GetMapping("/user/{id}")
    public ApiResponse<?> getBookingByUser(@PathVariable Long id) {
        List<BookingGetResponse> bookingResponse = bookingService.getBookingByUserId(id);
        return ApiResponse.builder()
                .code(1000)
                .message("Get booking by user successfully")
                .metadata(bookingResponse)
                .build();
    }

    @GetMapping("/hotel/{id}")
    public ApiResponse<?> getBookingByHotel(@PathVariable Long id) {
        List<BookingGetWithUserResponse> bookingResponse = bookingService.getBookingByHotelId(id);
        return ApiResponse.builder()
                .code(1000)
                .message("Get booking by hotel successfully")
                .metadata(bookingResponse)
                .build();
    }

    @PostMapping("/delete/{id}")
    public ApiResponse<?> deleteById(@PathVariable Long id) {
        BookingResponse bookingResponse = bookingService.deleteBookingById(id);
        return ApiResponse.builder()
                .code(1000)
                .message("Cancel booking successfully")
                .metadata(bookingResponse)
                .build();
    }
}
