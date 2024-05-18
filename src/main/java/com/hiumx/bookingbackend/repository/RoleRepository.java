package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
