package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidTokenRepository extends JpaRepository<InvalidToken, String> {
}
