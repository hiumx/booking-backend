package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.PostRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.PostResponse;
import com.hiumx.bookingbackend.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @PostMapping
    public ApiResponse<?> create(@RequestBody PostRequest request) {
        PostResponse response = postService.create(request);
        return ApiResponse.builder()
                .code(1000)
                .message("Create new post successfully")
                .metadata(response)
                .build();
    }

    @PostMapping("/confirm")
    public ApiResponse<?> confirmAPost(@RequestParam(value = "pid") Long postId) {
        PostResponse response = postService.confirmAPost(postId);
        return ApiResponse.builder()
                .code(1000)
                .message("Confirm a post successfully")
                .metadata(response)
                .build();
    }

    @GetMapping("")
    public ApiResponse<?> getAllPosts() {
        List<PostResponse> response = postService.getAllPosts();
        return ApiResponse.builder()
                .code(1000)
                .message("Get all posts successfully")
                .metadata(response)
                .build();
    }

    @GetMapping("/{pid}")
    public ApiResponse<?> getPostById(@PathVariable("pid") Long postId) {
        PostResponse response = postService.getPostById(postId);
        return ApiResponse.builder()
                .code(1000)
                .message("Get post successfully")
                .metadata(response)
                .build();
    }

    @GetMapping("/confirmed/{pid}")
    public ApiResponse<?> getPostConfirmedById(@PathVariable("pid") Long postId) {
        PostResponse response = postService.getPostConfirmedById(postId);
        return ApiResponse.builder()
                .code(1000)
                .message("Get post confirmed successfully")
                .metadata(response)
                .build();
    }

    @GetMapping("/hotel")
    public ApiResponse<?> getPostsByHotel(@RequestParam("hid") Long hotelId) {
        List<PostResponse> response = postService.getPostsByHotel(hotelId);
        return ApiResponse.builder()
                .code(1000)
                .message("Get posts by hotel successfully")
                .metadata(response)
                .build();
    }

    @GetMapping("/confirmed")
    public ApiResponse<?> getPostConfirmed() {
        List<PostResponse> response = postService.getPostsConfirmed();
        return ApiResponse.builder()
                .code(1000)
                .message("Get have been confirmed post successfully")
                .metadata(response)
                .build();
    }
}
