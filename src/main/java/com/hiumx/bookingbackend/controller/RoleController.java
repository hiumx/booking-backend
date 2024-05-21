package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.RoleRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.RoleResponse;
import com.hiumx.bookingbackend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    private RoleService roleService;

    @PostMapping
    public ApiResponse<?> createRole(@RequestBody RoleRequest roleRequest) {
        RoleResponse roleSaved = roleService.createRole(roleRequest);
        return ApiResponse.builder()
                .code(1000)
                .message("Create role successfully")
                .metadata(roleSaved)
                .build();
    }

    @GetMapping
    public ApiResponse<?> getAllRoles() {
        return ApiResponse.builder()
                .code(1000)
                .message("Get all roles successfully")
                .metadata(roleService.getAllRoles())
                .build();
    }

    @GetMapping("{id}")
    public ApiResponse<?> getRoleById(@PathVariable("id") Long id) {
        RoleResponse roleFounded = roleService.getRoleById(id);
        return ApiResponse.builder()
                .code(1000)
                .message("Get role successfully")
                .metadata(roleFounded)
                .build();
    }
}
