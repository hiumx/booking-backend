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
    private TypeHotelResponse typeHotelResponse;
    private Set<ConvenientResponse> convenientsResponse;
    private Set<ImageResponse> imagesResponse;
    private Set<RoomCreationResponse> roomResponses;
    private UserCreationResponse manager;
}
