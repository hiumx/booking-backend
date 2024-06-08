
package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.SearchFilterRequest;
import com.hiumx.bookingbackend.dto.request.SearchRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.HotelSearchAllResponse;
import com.hiumx.bookingbackend.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/api/v1/search")
public class SearchController {

    private SearchService searchService;

    @PostMapping
    public ApiResponse<?> search(@RequestBody SearchRequest request) {
        List<HotelSearchAllResponse> result = searchService.search(request);
        return ApiResponse.builder()
                .code(1000)
                .message("Search successfully")
                .metadata(result)
                .build();
    }

    @PostMapping("/filter")
    public ApiResponse<?> searchByCheckbox(@RequestBody SearchFilterRequest request) {
        List<HotelSearchAllResponse> result = searchService.filterByCheckbox(request);
        return ApiResponse.builder()
                .code(1000)
                .message("Filter by checked successfully")
                .metadata(result)
                .build();
    }
}
