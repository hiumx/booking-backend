package com.hiumx.bookingbackend.dto.response;

import com.hiumx.bookingbackend.entity.TypeHotel;
import com.hiumx.bookingbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HotelResponse {
    private Long id;
    private String name;
    private String description;
    private String location;
    private Float rate;
    private TypeHotelResponse typeHotel;
    private Set<ConvenientResponse> convenients;
    private Set<ImageResponse> images;
    private Set<RoomCreationResponse> rooms;
    private Set<ReviewResponse> reviews;
    private UserCreationResponse manager;
}
