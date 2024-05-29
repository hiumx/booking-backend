package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.response.ConvenientResponse;
import com.hiumx.bookingbackend.entity.Convenient;
import com.hiumx.bookingbackend.mapper.ConvenientMapper;
import com.hiumx.bookingbackend.repository.ConvenientRepository;
import com.hiumx.bookingbackend.service.ConvenientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConvenientServiceImpl implements ConvenientService {

    private ConvenientRepository convenientRepository;
    @Override
    public List<ConvenientResponse> getAll() {
        List<Convenient> convenients = convenientRepository.findAll();
        return convenients.stream().map(ConvenientMapper::toConvenientResponse).toList();
    }
}
