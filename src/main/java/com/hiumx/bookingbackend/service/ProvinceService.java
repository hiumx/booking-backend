package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.ProvinceRequest;
import com.hiumx.bookingbackend.dto.response.ProvinceResponse;

import java.util.List;

public interface ProvinceService {
    ProvinceResponse create(ProvinceRequest request);

    List<ProvinceResponse> getAll();
}
