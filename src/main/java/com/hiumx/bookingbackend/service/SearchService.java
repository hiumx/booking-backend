package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.SearchFilterRequest;
import com.hiumx.bookingbackend.dto.request.SearchRequest;
import com.hiumx.bookingbackend.dto.response.HotelSearchAllResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface SearchService {
    List<HotelSearchAllResponse> search(SearchRequest request);

    List<HotelSearchAllResponse> filterByCheckbox(SearchFilterRequest request);

//    List<HotelSearchAllResponse> getSearchHotel(Map<Long, Long> resultSearch);
}
