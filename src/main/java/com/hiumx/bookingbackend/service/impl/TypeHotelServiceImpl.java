package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.response.TypeHotelResponse;
import com.hiumx.bookingbackend.mapper.TypeHotelMapper;
import com.hiumx.bookingbackend.repository.TypeHotelRepository;
import com.hiumx.bookingbackend.service.TypeHotelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TypeHotelServiceImpl implements TypeHotelService {

    private TypeHotelRepository typeHotelRepository;
    @Override
    public List<TypeHotelResponse> getAll() {
        return typeHotelRepository.findAll().stream().map(TypeHotelMapper::toTypeHotelResponse).toList();
    }
}
