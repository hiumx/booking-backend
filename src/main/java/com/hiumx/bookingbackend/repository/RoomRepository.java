package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
