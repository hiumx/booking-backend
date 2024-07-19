package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.HistorySearchRequest;
import com.hiumx.bookingbackend.dto.response.HistorySearchResponse;

import java.util.List;

public interface HistorySearchService {

    public HistorySearchResponse create(HistorySearchRequest request);
    public List<HistorySearchResponse> getHistorySearches(Long userId);
}
