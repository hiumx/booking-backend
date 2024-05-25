package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.dto.GenderDto;
import com.hiumx.bookingbackend.dto.response.ImageResponse;
import com.hiumx.bookingbackend.entity.Gender;
import com.hiumx.bookingbackend.entity.Image;

public class ImageMapper {
//    public static Gender toImage (ImageReque genderDto) {
//        return new Gender(
//                genderDto.getId(),
//                genderDto.getName()
//        );
//    }

    public static ImageResponse toImageResponse(Image image) {
        return ImageResponse.builder()
                .id(image.getId())
                .url(image.getUrl())
                .hotelId(image.getHotel().getId())
                .build();
    }
}