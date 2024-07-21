package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.dto.request.PostRequest;
import com.hiumx.bookingbackend.dto.response.HotelResponse;
import com.hiumx.bookingbackend.dto.response.PostResponse;
import com.hiumx.bookingbackend.entity.Post;

public class PostMapper {
    public static Post toPost (PostRequest request) {
        return Post.builder()
                .title(request.getTitle())
                .contentHtml(request.getContentHtml())
                .contentMarkdown(request.getContentMarkdown())
                .image(request.getImage())
                .build();
    }

    public static PostResponse toPostResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .contentHtml(post.getContentHtml())
                .contentMarkdown(post.getContentMarkdown())
                .typePost(TypePostMapper.toTypePostResponse(post.getTypePost()))
                .hotel(HotelMapper.toHotelResponse(post.getHotel()))
                .image(post.getImage())
                .isConfirm(post.getIsConfirm())
                .build();
    }
}
