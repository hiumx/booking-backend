package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.ProvinceRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.ProvinceResponse;
import com.hiumx.bookingbackend.service.ProvinceService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/provinces")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ProvinceController {

    private ProvinceService provinceService;

    @PostMapping
    public ApiResponse<?> create(@RequestBody ProvinceRequest request) {
        ProvinceResponse provinceResponse = provinceService.create(request);
        return ApiResponse.builder()
                .code(1000)
                .message("Create new province successfully")
                .metadata(provinceResponse)
                .build();
    }

    @GetMapping
    public ApiResponse<?> getAll() {
        List<ProvinceResponse> provinceResponse = provinceService.getAll();
        return ApiResponse.builder()
                .code(1000)
                .message("Get all provinces successfully")
                .metadata(provinceResponse)
                .build();
    }

}
