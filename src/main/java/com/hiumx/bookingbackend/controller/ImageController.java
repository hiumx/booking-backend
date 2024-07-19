package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.ImageRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.ImageResponse;
import com.hiumx.bookingbackend.service.ImageService;
import com.hiumx.bookingbackend.service.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/api/v1/images")
public class ImageController {

    private ImageService imageService;
    private S3Service s3Service;

    @PostMapping
    public ApiResponse<?> upload(@RequestParam("files") MultipartFile[] files,@RequestParam Long hotel_id) {

        List<String> urls = s3Service.uploadFiles(files);
        ImageRequest imageRequest = ImageRequest.builder()
                .url(new HashSet<>(urls))
                .hotel_id(hotel_id)
                .build();
        List<ImageResponse> listRes = imageService.upload(imageRequest);
        return ApiResponse.builder()
                .code(1000)
                .message("Upload images successfully")
                .metadata(listRes)
                .build();
    }

    @GetMapping("{hotelId}")
    public ApiResponse<?> getImagesByHotelId(@PathVariable("hotelId") Long hotelId) {
        List<ImageResponse> imageResponses = imageService.findByHotelId(hotelId);
        return ApiResponse.builder()
                .code(1000)
                .message("Get all images by hotel id successfully")
                .metadata(imageResponses)
                .build();
    }

    @GetMapping()
    public ApiResponse<?> getAll() {
        List<ImageResponse> imageResponses = imageService.getAll();
        return ApiResponse.builder()
                .code(1000)
                .message("Get all images successfully")
                .metadata(imageResponses)
                .build();
    }
}
