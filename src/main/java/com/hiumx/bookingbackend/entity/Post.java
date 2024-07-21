package com.hiumx.bookingbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
@Builder
@Data
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "content_html", columnDefinition = "TEXT")
    private String contentHtml;

    @Column(name = "content_markdown", columnDefinition = "TEXT")
    private String contentMarkdown;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypePost typePost;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "image")
    private String image;

    @Column(name = "is_confirm")
    private Boolean isConfirm;

    @Column(name = "is_block")
    private Boolean isBlock;
}
