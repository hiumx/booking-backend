package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.request.PermissionRequest;
import com.hiumx.bookingbackend.dto.response.PermissionResponse;
import com.hiumx.bookingbackend.entity.Permission;
import com.hiumx.bookingbackend.mapper.PermissionMapper;
import com.hiumx.bookingbackend.repository.PermissionRepository;
import com.hiumx.bookingbackend.service.PermissionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private PermissionRepository permissionRepository;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = PermissionMapper.toPermission(request);
        Permission permissionSaved = permissionRepository.save(permission);
        return PermissionMapper.toPermissionResponse(permissionSaved);
    }

    public List<PermissionResponse> getAll() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream().map(PermissionMapper::toPermissionResponse).toList();
    }

    public void delete(Long id) {
        permissionRepository.deleteById(id);
    }
}
