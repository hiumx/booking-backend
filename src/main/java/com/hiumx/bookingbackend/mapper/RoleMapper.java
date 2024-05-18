package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.dto.RoleDto;
import com.hiumx.bookingbackend.entity.Role;

public class RoleMapper {
    public static Role mapToRole(RoleDto roleDto) {
        return new Role(
                roleDto.getId(),
                roleDto.getName(),
                roleDto.getDescription()
        );
    }

    public static RoleDto mapToRoleDto(Role role) {
        return new RoleDto(
                role.getId(),
                role.getName(),
                role.getDescription()
        );
    }
}
