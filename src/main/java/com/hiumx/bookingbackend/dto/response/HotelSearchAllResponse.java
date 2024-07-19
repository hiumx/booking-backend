package com.hiumx.bookingbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HotelSearchAllResponse {
    private Long id;
    private String name;
    private String location;
    private Float rate;
    private TypeHotelResponse typeHotel;
    private ImageResponse image;
    private List<ReviewGetAllHotelResponse> reviews;
    private List<RoomCreationResponse> rooms;
    private Float fromCenter;
}
