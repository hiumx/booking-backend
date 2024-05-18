package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.dto.GenderDto;
import com.hiumx.bookingbackend.entity.Gender;

public class GenderMapper {
    public static Gender mapToGender(GenderDto genderDto) {
        return new Gender(
                genderDto.getId(),
                genderDto.getName()
        );
    }

    public static GenderDto mapToGenderDto(Gender gender) {
        return new GenderDto(
                gender.getId(),
                gender.getName()
        );
    }
}
