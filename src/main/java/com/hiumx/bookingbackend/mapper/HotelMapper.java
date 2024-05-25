package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.dto.request.HotelRequest;
import com.hiumx.bookingbackend.dto.response.HotelResponse;
import com.hiumx.bookingbackend.entity.Hotel;
import com.hiumx.bookingbackend.entity.User;

public class HotelMapper {
    public static Hotel toHotel(HotelRequest request) {
        return Hotel.builder()
                .name(request.getName())
                .description(request.getDescription())
                .location(request.getLocation())
                .rate(request.getRate())
                .build();
    }

    public static HotelResponse toHotelResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .location(hotel.getLocation())
                .description(hotel.getDescription())
                .rate(hotel.getRate())
                .manager(UserMapper.toUserResponse(hotel.getManagerId()))
                .build();
    }
}
