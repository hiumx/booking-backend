package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.dto.request.RoomRequest;
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
                .hotel(room.getHotel())
                .numberBed(room.getNumberBed())
                .price(room.getPrice())
                .build();
    }
}
