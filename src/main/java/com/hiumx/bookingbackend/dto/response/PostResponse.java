package com.hiumx.bookingbackend.dto.response;

import com.hiumx.bookingbackend.entity.Hotel;
import com.hiumx.bookingbackend.entity.TypePost;
import com.hiumx.bookingbackend.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostResponse {
    private Long id;
    private String title;
    private String contentHtml;
    private String contentMarkdown;
    private TypePostResponse typePost;
    private HotelResponse hotel;
    private String image;
    private boolean isConfirm;
}
