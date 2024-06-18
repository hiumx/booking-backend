package com.hiumx.bookingbackend.dto.response;

import com.hiumx.bookingbackend.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RoomResponse {
    private Long id;
    private String name;
    private HotelResponse hotel;
    private Integer numberBed;
    private Long price;
}
