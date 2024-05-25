package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.service.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/uploads")
@AllArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
public class UploadController {

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;

    @Autowired
    private S3Service s3Service;

    @Autowired
    public UploadController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping
    public ApiResponse<?> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        List<String> urls = s3Service.uploadFiles(files);
//        List<String> fileUrls = keys.stream()
//                .map(key -> "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key)
//                .toList();
        return ApiResponse.builder()
                .code(1000)
                .message("Upload multiple files successfully")
                .metadata(urls)
                .build();
    }
}
