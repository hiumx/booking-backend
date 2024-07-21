package com.hiumx.bookingbackend.controller;


import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.TypeHotelResponse;
import com.hiumx.bookingbackend.dto.response.TypePostResponse;
import com.hiumx.bookingbackend.service.TypeHotelService;
import com.hiumx.bookingbackend.service.TypePostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/api/v1/type-post")
public class TypePostController {

    private TypePostService typePostService;

    @GetMapping
    public ApiResponse<?> getAll() {
        List<TypePostResponse> res = typePostService.getAll();
        return ApiResponse.builder()
                .code(1000)
                .message("Get all types of post successfully")
                .metadata(res)
                .build();
    }
}
