package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.dto.response.ConvenientResponse;
import com.hiumx.bookingbackend.entity.Convenient;

public class ConvenientMapper {
    public static ConvenientResponse toConvenientResponse(Convenient convenient) {
        return ConvenientResponse.builder()
                .id(convenient.getId())
                .name(convenient.getName())
                .build();
    }
}
