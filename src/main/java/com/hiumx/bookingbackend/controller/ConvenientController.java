package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.ConvenientRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.ConvenientResponse;
import com.hiumx.bookingbackend.service.ConvenientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/convenients")
@CrossOrigin(value = "http://localhost:3000")
@AllArgsConstructor
public class ConvenientController {

    private ConvenientService convenientService;

    @GetMapping
    public ApiResponse<?> getAll() {
        List<ConvenientResponse> convenientResponses = convenientService.getAll();
        return ApiResponse.builder()
                .code(1000)
                .message("Get all convenients successfully")
                .metadata(convenientResponses)
                .build();
    }

    @PostMapping
    public ApiResponse<?> create(@RequestBody ConvenientRequest request) {
        ConvenientResponse convenientResponse = convenientService.create(request);
        return ApiResponse.builder()
                .code(1000)
                .metadata("Create convenient successfully")
                .metadata(convenientResponse)
                .build();
    }
}
