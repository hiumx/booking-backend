package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.document.HotelDocument;
import com.hiumx.bookingbackend.dto.request.HotelRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.HotelGetAllResponse;
import com.hiumx.bookingbackend.dto.response.HotelResponse;
import com.hiumx.bookingbackend.dto.response.HotelSearchAllResponse;
import com.hiumx.bookingbackend.service.HotelService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping
    public ApiResponse<?> getAll() {
        List<HotelGetAllResponse> res = hotelService.getAll();
        return ApiResponse.builder()
                .code(1000)
                .message("Get all hotels successfully")
                .metadata(res)
                .build();
    }

    @GetMapping("{id}")
    public ApiResponse<?> getById(@PathVariable Long id) {
        HotelResponse res = hotelService.getById(id);
        return ApiResponse.builder()
                .code(1000)
                .message("Get all hotels successfully")
                .metadata(res)
                .build();
    }

    @GetMapping("/find-by-location")
    public ApiResponse<?> getByLocation(@RequestParam("location") String location) {
        List<HotelDocument> res = hotelService.getByLocation(location);
        return ApiResponse.builder()
                .code(1000)
                .message("Get all hotels successfully")
                .metadata(res)
                .build();
    }

//    @GetMapping("/search-result")
//    public ApiResponse<?> getSearchResult() {
//        List<HotelSearchAllResponse> res = hotelService.getSearchHotel();
//        return ApiResponse.builder()
//                .code(1000)
//                .message("Get result of search hotel successfully")
//                .metadata(res)
//                .build();
//    }
}
