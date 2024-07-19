package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.GenderDto;
import com.hiumx.bookingbackend.dto.request.HistorySearchRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.HistorySearchResponse;
import com.hiumx.bookingbackend.service.HistorySearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/history-search")
@AllArgsConstructor
public class HistorySearchController {

    private HistorySearchService historySearchService;

    @PostMapping
    ApiResponse<?> create(@RequestBody HistorySearchRequest request) {
        HistorySearchResponse historySearchResponse = historySearchService.create(request);
        return ApiResponse.builder()
                .code(1000)
                .message("Create history search successfully")
                .metadata(historySearchResponse)
                .build();
    }

    @GetMapping
    ApiResponse<?> getHistorySearch(@RequestParam(name = "uid") Long userId) {
        List<HistorySearchResponse> historySearchResponse = historySearchService.getHistorySearches(userId);
        return ApiResponse.builder()
                .code(1000)
                .message("Create history search successfully")
                .metadata(historySearchResponse)
                .build();

    }
}