package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.GenderDto;
import com.hiumx.bookingbackend.entity.Gender;
import com.hiumx.bookingbackend.mapper.GenderMapper;
import com.hiumx.bookingbackend.repository.GenderRepository;
import com.hiumx.bookingbackend.service.GenderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GenderServiceImpl implements GenderService {
    private GenderRepository genderRepository;

    @Override
    public GenderDto createGender(GenderDto genderDto) {
        Gender gender = GenderMapper.mapToGender(genderDto);
        Gender genderSaved = genderRepository.save(gender);
        return GenderMapper.mapToGenderDto(genderSaved);
    }
}
