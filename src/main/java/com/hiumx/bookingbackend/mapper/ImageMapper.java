package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.document.HotelDocument;
import com.hiumx.bookingbackend.document.ImageDocument;
import com.hiumx.bookingbackend.dto.GenderDto;
import com.hiumx.bookingbackend.dto.response.ImageResponse;
import com.hiumx.bookingbackend.entity.Gender;
import com.hiumx.bookingbackend.entity.Image;

public class ImageMapper {
    public static ImageResponse toImageResponse(Image image) {
        return ImageResponse.builder()
                .id(image.getId())
                .url(image.getUrl())
                .build();
    }

    public static ImageResponse toImageResponseFromDocument(ImageDocument image) {
        return ImageResponse.builder()
                .id(image.getId())
                .url(image.getUrl())
                .build();
    }

    public static ImageDocument toImageDocument(Image image) {
        return ImageDocument.builder()
                .id(image.getId())
                .url(image.getUrl())
                .hotelId(image.getHotel().getId())
                .build();
    }
}
