package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {
}
