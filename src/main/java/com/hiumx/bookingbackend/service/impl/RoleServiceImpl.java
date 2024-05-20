package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.RoleDto;
import com.hiumx.bookingbackend.entity.Role;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.mapper.RoleMapper;
import com.hiumx.bookingbackend.repository.RoleRepository;
import com.hiumx.bookingbackend.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = RoleMapper.mapToRole(roleDto);
        Role roleSaved = roleRepository.save(role);
        return RoleMapper.mapToRoleDto(roleSaved);
    }

    @Override
    public RoleDto getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.ROLE_NOT_FOUND));
        return RoleMapper.mapToRoleDto(role);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role -> RoleMapper.mapToRoleDto(role)).toList();
    }
}
