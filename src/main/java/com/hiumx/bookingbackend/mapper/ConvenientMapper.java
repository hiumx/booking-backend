package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.document.ConvenientDocument;
import com.hiumx.bookingbackend.dto.response.ConvenientResponse;
import com.hiumx.bookingbackend.entity.Convenient;

public class ConvenientMapper {
    public static ConvenientResponse toConvenientResponse(Convenient convenient) {
        return ConvenientResponse.builder()
                .id(convenient.getId())
                .name(convenient.getName())
                .build();
    }

    public static ConvenientResponse toConvenientResponseFromDocument(ConvenientDocument convenient) {
        return ConvenientResponse.builder()
                .id(convenient.getId())
                .name(convenient.getName())
                .build();
    }

    public static ConvenientDocument toConvenientDocument(Convenient convenient) {
        return ConvenientDocument.builder()
                .id(convenient.getId())
                .name(convenient.getName())
                .build();
    }
}
