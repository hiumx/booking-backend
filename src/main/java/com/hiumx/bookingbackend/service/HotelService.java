package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.HotelRequest;
import com.hiumx.bookingbackend.dto.response.HotelGetAllResponse;
import com.hiumx.bookingbackend.dto.response.HotelResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HotelService {
    HotelResponse create(HotelRequest request);
    List<HotelGetAllResponse> getAll();
    HotelResponse getById(Long id);
}
