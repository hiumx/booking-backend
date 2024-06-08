package com.hiumx.bookingbackend.service;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface S3Service {
    List<String> uploadFiles(MultipartFile[] files);
//    String uploadFile(String fileName, BufferedImage image) throws IOException;
}
