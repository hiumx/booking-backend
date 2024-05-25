package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.HotelRequest;
import com.hiumx.bookingbackend.dto.response.HotelResponse;
import org.springframework.web.multipart.MultipartFile;

public interface HotelService {
    public HotelResponse create(HotelRequest request);
}
