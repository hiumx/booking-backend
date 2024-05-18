package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.RoleDto;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
        RoleDto roleSaved = roleService.createRole(roleDto);
        return ResponseEntity.ok(roleSaved);
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
        RoleDto roleFounded = roleService.getRoleById(id);
        return ApiResponse.builder()
                .code(1000)
                .message("Get role successfully")
                .metadata(roleFounded)
                .build();
    }
}
