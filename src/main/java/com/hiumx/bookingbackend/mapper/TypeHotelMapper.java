package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.document.TypeHotelDocument;
import com.hiumx.bookingbackend.dto.request.TypeHotelRequest;
import com.hiumx.bookingbackend.dto.response.TypeHotelResponse;
import com.hiumx.bookingbackend.entity.TypeHotel;

public class TypeHotelMapper {
    public static TypeHotel toTypeHotel(TypeHotelRequest request) {
        return TypeHotel.builder()
                .name(request.getName())
                .build();
    }

    public static TypeHotelResponse toTypeHotelResponse(TypeHotel typeHotel) {
        return TypeHotelResponse.builder()
                .id(typeHotel.getId())
                .name(typeHotel.getName())
                .image(typeHotel.getImage())
                .build();
    }

    public static TypeHotelResponse toTypeHotelResponseFromDocument(TypeHotelDocument typeHotel) {
        return TypeHotelResponse.builder()
                .id(typeHotel.getId())
                .name(typeHotel.getName())
                .build();
    }

    public static TypeHotelDocument toTypeHotelDocument(TypeHotel typeHotel) {
        return TypeHotelDocument.builder()
                .id(typeHotel.getId())
                .name(typeHotel.getName())
                .build();
    }
}
