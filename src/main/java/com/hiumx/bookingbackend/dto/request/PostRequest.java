package com.hiumx.bookingbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostRequest {
    private String title;
    private String contentHtml;
    private String contentMarkdown;
    private Long authorId;
    private Long hotelId;
    private Long typePostId;
    private String image = "https://d8271hh5ynwda.cloudfront.net/post_img.jpg";
}
