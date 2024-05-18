package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);
    RoleDto getRoleById(Long id);

    List<RoleDto> getAllRoles();
}
