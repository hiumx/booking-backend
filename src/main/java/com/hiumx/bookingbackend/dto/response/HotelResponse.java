package com.hiumx.bookingbackend.dto.response;

import com.hiumx.bookingbackend.entity.TypeHotel;
import com.hiumx.bookingbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HotelResponse {
    private Long id;
    private String name;
    private String description;
    private TypeHotel typeHotel;
    private String location;
    private Float rate;
    private User managerId;
}
