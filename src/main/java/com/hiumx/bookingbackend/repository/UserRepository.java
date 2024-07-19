package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByPhone(String phone);
    boolean existsByEmail(String email);

//    @Query("SELECT u FROM User u JOIN u.hotelsSaved h WHERE u.id = ?1")
//    User findUserWithSavedHotels(Long userId);
}
