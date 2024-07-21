package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.response.TypeHotelResponse;
import com.hiumx.bookingbackend.dto.response.TypePostResponse;
import com.hiumx.bookingbackend.mapper.TypeHotelMapper;
import com.hiumx.bookingbackend.mapper.TypePostMapper;
import com.hiumx.bookingbackend.repository.TypeHotelRepository;
import com.hiumx.bookingbackend.repository.TypePostRepository;
import com.hiumx.bookingbackend.service.TypeHotelService;
import com.hiumx.bookingbackend.service.TypePostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TypePostServiceImpl implements TypePostService {

    private TypePostRepository typePostRepository;
    @Override
    public List<TypePostResponse> getAll() {
        return typePostRepository.findAll().stream().map(TypePostMapper::toTypePostResponse).toList();
    }
}
