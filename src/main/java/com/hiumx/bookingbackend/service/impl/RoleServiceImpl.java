package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.request.RoleRequest;
import com.hiumx.bookingbackend.dto.response.RoleResponse;
import com.hiumx.bookingbackend.entity.Role;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.mapper.RoleMapper;
import com.hiumx.bookingbackend.repository.PermissionRepository;
import com.hiumx.bookingbackend.repository.RoleRepository;
import com.hiumx.bookingbackend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private PermissionRepository permissionRepository;
    @Override
    public RoleResponse createRole(RoleRequest request) {
        Role role = RoleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        Role roleSaved = roleRepository.save(role);
        return RoleMapper.toRoleResponse(roleSaved);
    }

    @Override
    public RoleResponse getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.ROLE_NOT_FOUND));
        return RoleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(RoleMapper::toRoleResponse).toList();
    }
}
