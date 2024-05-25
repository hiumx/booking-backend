package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.RoomRequest;
import com.hiumx.bookingbackend.dto.response.RoomResponse;

public interface RoomService {
    RoomResponse create(RoomRequest request);
}
