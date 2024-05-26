package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.response.TypeHotelResponse;

import java.util.List;

public interface TypeHotelService {
    List<TypeHotelResponse> getAll();
}
