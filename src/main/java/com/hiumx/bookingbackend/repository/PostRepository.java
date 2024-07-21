package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.HistorySearch;
import com.hiumx.bookingbackend.entity.Hotel;
import com.hiumx.bookingbackend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM posts WHERE is_confirm = true", nativeQuery = true)
    List<Post> findPostConfirmed();
    List<Post> findByHotel(Hotel hotel);

    List<Post> findByIsConfirm(Boolean isConfirm);
}
