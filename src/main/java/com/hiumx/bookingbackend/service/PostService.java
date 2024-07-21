package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.PostRequest;
import com.hiumx.bookingbackend.dto.response.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse create(PostRequest request);
    PostResponse confirmAPost(Long postId);
    List<PostResponse> getPostsByHotel(Long hotelId);
    PostResponse getPostById(Long postId);
    PostResponse getPostConfirmedById(Long postId);
    List<PostResponse> getAllPosts();
    List<PostResponse> getPostsConfirmed();
}
