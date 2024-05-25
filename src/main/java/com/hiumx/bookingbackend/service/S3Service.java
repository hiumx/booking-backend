package com.hiumx.bookingbackend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface S3Service {
    List<String> uploadFiles(MultipartFile[] files);
}
