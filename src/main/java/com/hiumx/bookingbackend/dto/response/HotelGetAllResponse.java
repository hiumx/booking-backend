package com.hiumx.bookingbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HotelGetAllResponse {
    private Long id;
    private String name;
    private String description;
    private String location;
    private Float rate;
    private TypeHotelResponse typeHotelResponse;
    private List<ImageResponse> imagesResponse;
    private Long managerId;
}
