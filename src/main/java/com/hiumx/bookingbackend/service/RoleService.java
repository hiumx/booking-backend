package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.RoleRequest;
import com.hiumx.bookingbackend.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    public RoleResponse createRole(RoleRequest request);
    public RoleResponse getRoleById(Long id);

    List<RoleResponse> getAllRoles();
}
