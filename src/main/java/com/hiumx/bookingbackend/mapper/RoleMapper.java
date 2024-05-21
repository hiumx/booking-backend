package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.dto.request.RoleRequest;
import com.hiumx.bookingbackend.dto.response.PermissionResponse;
import com.hiumx.bookingbackend.dto.response.RoleResponse;
import com.hiumx.bookingbackend.entity.Role;

import java.util.HashSet;

public class RoleMapper {
    public static Role toRole(RoleRequest request) {
        return Role.builder()
                .name(request.getName())
                .description((request.getDescription()))
                .build();
    }

    public static RoleResponse toRoleResponse(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .permissions(new HashSet<>(role.getPermissions().stream().map(PermissionMapper::toPermissionResponse).toList()))
                .build();
    }
}
