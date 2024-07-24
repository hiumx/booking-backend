package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.document.HotelDocument;
import com.hiumx.bookingbackend.dto.request.HotelRequest;
import com.hiumx.bookingbackend.dto.response.HotelGetAllResponse;
import com.hiumx.bookingbackend.dto.response.HotelResponse;
import com.hiumx.bookingbackend.dto.response.HotelSearchAllResponse;
import com.hiumx.bookingbackend.entity.Hotel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface HotelService {
    HotelResponse create(HotelRequest request);
    List<HotelGetAllResponse> getAll();
    HotelResponse getById(Long id);
//    HotelResponse getSearchById(Long id);

    List<HotelDocument> getByLocation(String location);
    List<HotelSearchAllResponse> getTopHighRating();
    List<HotelResponse> getByManagerId(Long managerId);
    List<Object[]> countHotelsByLocation();

}
