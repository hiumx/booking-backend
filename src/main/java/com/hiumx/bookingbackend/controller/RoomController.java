package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.RoomRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.RoomGetResponse;
import com.hiumx.bookingbackend.dto.response.RoomResponse;
import com.hiumx.bookingbackend.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private RoomService roomService;

    @PostMapping
    public ApiResponse<?> create(@RequestBody RoomRequest request) {
        RoomResponse roomResponse = roomService.create(request);
        return ApiResponse.builder()
                .code(1000)
                .message("Create new room successfully")
                .metadata(roomResponse)
                .build();
    }

    @GetMapping("{hotelId}")
    public ApiResponse<?> getRoomsByHotelId(@PathVariable("hotelId") Long hotelId) {
        List<RoomGetResponse> roomsResponse = roomService.getRoomsByHotelId(hotelId);
        return ApiResponse.builder()
                .code(1000)
                .message("Get all rooms by hotel id successfully")
                .metadata(roomsResponse)
                .build();
    }

    @GetMapping("/r")
    public ApiResponse<?> getRoomsById(@RequestParam("rid") Long roomId) {
        RoomResponse roomResponse = roomService.getRoomById(roomId);
        System.out.println(roomResponse);
        return ApiResponse.builder()
                .code(1000)
                .message("Get room by id successfully")
                .metadata(roomResponse)
                .build();
    }

    @GetMapping()
    public ApiResponse<?> getAll() {
        List<RoomGetResponse> roomsResponse = roomService.getAll();
        return ApiResponse.builder()
                .code(1000)
                .message("Get all rooms successfully")
                .metadata(roomsResponse)
                .build();
    }
}
