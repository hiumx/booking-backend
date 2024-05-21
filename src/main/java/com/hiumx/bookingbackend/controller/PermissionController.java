package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.PermissionRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.PermissionResponse;
import com.hiumx.bookingbackend.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permissions")
@AllArgsConstructor
public class PermissionController {
    private PermissionService permissionService;
    @PostMapping
    public ApiResponse<?> create(@RequestBody PermissionRequest request) {
        PermissionResponse res = permissionService.create(request);
        return ApiResponse.builder()
                .code(1000)
                .message("Create permission successfully")
                .metadata(res)
                .build();
    }

    @GetMapping
    public ApiResponse<?> getAll() {
        List<PermissionResponse> permissionResponses = permissionService.getAll();
        return ApiResponse.builder()
                .code(1000)
                .message("Get all permissions successfully")
                .metadata(permissionResponses)
                .build();
    }
    @DeleteMapping("{id}")
    public ApiResponse<?> delete(@PathVariable("id") Long id) {
        permissionService.delete(id);
        return ApiResponse.builder()
                .code(1000)
                .message("Delete permission successfully")
                .build();
    }
}
