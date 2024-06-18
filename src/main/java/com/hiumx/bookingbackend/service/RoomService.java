package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.RoomRequest;
import com.hiumx.bookingbackend.dto.response.RoomGetResponse;
import com.hiumx.bookingbackend.dto.response.RoomResponse;

import java.util.List;

public interface RoomService {
    RoomResponse create(RoomRequest request);
    List<RoomGetResponse> getRoomsByHotelId(Long hotelId);
    RoomResponse getRoomById(Long id);
    List<RoomGetResponse> getAll();
}
