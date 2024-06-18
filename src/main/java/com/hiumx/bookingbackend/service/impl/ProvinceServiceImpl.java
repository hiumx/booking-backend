package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.request.ProvinceRequest;
import com.hiumx.bookingbackend.dto.response.ProvinceResponse;
import com.hiumx.bookingbackend.entity.Province;
import com.hiumx.bookingbackend.mapper.ProvinceMapper;
import com.hiumx.bookingbackend.repository.ProvinceRepository;
import com.hiumx.bookingbackend.service.ProvinceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    private ProvinceRepository provinceRepository;

    @Override
    public ProvinceResponse create(ProvinceRequest request) {
        System.out.println(request);
        Province province = ProvinceMapper.toProvince(request);
        return ProvinceMapper.toProvinceResponse(provinceRepository.save(province));
    }

    @Override
    public List<ProvinceResponse> getAll() {
        List<Province> provinceList = provinceRepository.findAll();
        return provinceList.stream().map(ProvinceMapper::toProvinceResponse).toList();
    }
}
