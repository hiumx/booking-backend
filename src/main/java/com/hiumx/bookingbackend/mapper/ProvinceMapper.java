package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.dto.request.ProvinceRequest;
import com.hiumx.bookingbackend.dto.response.ProvinceResponse;
import com.hiumx.bookingbackend.entity.Province;

public class ProvinceMapper {
    public static Province toProvince(ProvinceRequest request) {
        return Province.builder()
                .provinceName(request.getProvinceName())
                .country(request.getCountry())
                .image(request.getImage())
                .build();
    }

    public static ProvinceResponse toProvinceResponse(Province province) {

        return ProvinceResponse.builder()
                .id(province.getId())
                .provinceName(province.getProvinceName())
                .country(province.getCountry())
                .image(province.getImage())
                .build();
    }
}
