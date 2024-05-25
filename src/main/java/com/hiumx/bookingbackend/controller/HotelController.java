package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.HotelRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.HotelResponse;
import com.hiumx.bookingbackend.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/api/v1/hotels")
public class HotelController {

    private HotelService hotelService;

    @PostMapping
    public ApiResponse<?> create(@RequestBody HotelRequest request) {
        System.out.println("Request: " + request);
        HotelResponse hotelResponse = hotelService.create(request);
        return ApiResponse.builder()
                .code(1000)
                .message("Create new hotel successfully")
                .metadata(hotelResponse)
                .build();
    }
}
