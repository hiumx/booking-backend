package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.PermissionRequest;
import com.hiumx.bookingbackend.dto.response.PermissionResponse;
import com.hiumx.bookingbackend.entity.Permission;
import com.hiumx.bookingbackend.mapper.PermissionMapper;

import java.util.List;

public interface PermissionService {
    public PermissionResponse create(PermissionRequest request);
    public List<PermissionResponse> getAll();
    public void delete(Long id);
}
