package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.response.TypeHotelResponse;
import com.hiumx.bookingbackend.dto.response.TypePostResponse;

import java.util.List;

public interface TypePostService {
    List<TypePostResponse> getAll();
}
