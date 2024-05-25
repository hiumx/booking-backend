package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.RoomRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.RoomResponse;
import com.hiumx.bookingbackend.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
