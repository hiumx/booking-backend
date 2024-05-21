package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
