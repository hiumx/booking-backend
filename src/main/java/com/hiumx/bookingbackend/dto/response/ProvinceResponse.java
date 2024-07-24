package com.hiumx.bookingbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProvinceResponse {
    private Long id;
    private String provinceName;
    private String country;
    private String image;
    private Long countHotel;
}
