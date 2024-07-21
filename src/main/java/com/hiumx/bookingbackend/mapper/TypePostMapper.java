package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.dto.response.TypePostResponse;
import com.hiumx.bookingbackend.entity.TypePost;

public class TypePostMapper {
    public static TypePostResponse toTypePostResponse(TypePost typePost) {
        return TypePostResponse.builder()
                .id(typePost.getId())
                .name(typePost.getName())
                .build();
    }
}
