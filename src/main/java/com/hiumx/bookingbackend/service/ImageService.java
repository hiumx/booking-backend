package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.ImageRequest;
import com.hiumx.bookingbackend.dto.response.ImageResponse;

import java.util.List;

public interface ImageService {
    List<ImageResponse> upload(ImageRequest request);
    List<ImageResponse> findByHotelId(Long hotelId);
    List<ImageResponse> getAll();
}
