package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.document.RoomDocument;
import com.hiumx.bookingbackend.dto.request.RoomRequest;
import com.hiumx.bookingbackend.dto.response.RoomCreationResponse;
import com.hiumx.bookingbackend.dto.response.RoomGetResponse;
import com.hiumx.bookingbackend.dto.response.RoomResponse;
import com.hiumx.bookingbackend.entity.Room;


public class RoomMapper {

    public static Room toRoom(RoomRequest request) {
        return Room.builder()
                .name(request.getName())
                .numberBed(request.getNumberBed())
                .price(request.getPrice())
                .build();

    }

    public static RoomResponse toRoomResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .name(room.getName())
                .hotel(HotelMapper.toHotelResponse(room.getHotel()))
                .numberBed(room.getNumberBed())
                .price(room.getPrice())
                .build();
    }

    public static RoomResponse toRoomResponse(RoomDocument room) {
        return RoomResponse.builder()
                .id(room.getId())
                .name(room.getName())
                .numberBed(room.getNumberBed())
                .price(room.getPrice())
                .build();
    }

    public static RoomCreationResponse toRoomCreationResponse(Room room) {
        return RoomCreationResponse.builder()
                .id(room.getId())
                .name(room.getName())
                .numberBed(room.getNumberBed())
                .price(room.getPrice())
                .build();
    }

    public static RoomCreationResponse toRoomCreationResponseFromDocument(RoomDocument room) {
        return RoomCreationResponse.builder()
                .id(room.getId())
                .name(room.getName())
                .numberBed(room.getNumberBed())
                .price(room.getPrice())
                .build();
    }

    public static RoomGetResponse toRoomGetResponse(Room room) {
        return RoomGetResponse.builder()
                .id(room.getId())
                .name(room.getName())
                .hotelId(room.getHotel().getId())
                .numberBed(room.getNumberBed())
                .price(room.getPrice())
                .build();
    }

    public static RoomDocument toRoomDocument(Room room) {
        return RoomDocument.builder()
                .id(room.getId())
                .name(room.getName())
                .hotelId(room.getHotel().getId())
                .numberBed(room.getNumberBed())
                .price(room.getPrice())
                .build();
    }
}
