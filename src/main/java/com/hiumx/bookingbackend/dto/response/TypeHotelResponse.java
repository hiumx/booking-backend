package com.hiumx.bookingbackend.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TypeHotelResponse {
    private Long id;
    private String name;
    private String image;
}
