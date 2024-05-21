package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.dto.request.PermissionRequest;
import com.hiumx.bookingbackend.dto.response.PermissionResponse;
import com.hiumx.bookingbackend.entity.Permission;

public class PermissionMapper {
    public static Permission toPermission(PermissionRequest request) {
        return Permission.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public static PermissionResponse toPermissionResponse(Permission permission) {
        return PermissionResponse.builder()
                .name(permission.getName())
                .description(permission.getDescription())
                .build();
    }
}
