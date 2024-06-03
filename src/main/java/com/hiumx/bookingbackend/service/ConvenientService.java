package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.ConvenientRequest;
import com.hiumx.bookingbackend.dto.response.ConvenientResponse;

import java.util.List;

public interface ConvenientService {
    List<ConvenientResponse> getAll();
    ConvenientResponse create(ConvenientRequest request);
}
