package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3ServiceImpl implements S3Service {

    @Autowired
    private final S3Client s3Client;

    @Value("${aws.cloudFront.preUrl}")
    private String prefixUrlCloudFront;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    public S3ServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public List<String> uploadFiles(MultipartFile[] files) {
        List<String> urls = new ArrayList<>();
        S3Presigner presigner = S3Presigner.create();
        for(MultipartFile file: files) {
            String key = Paths.get(System.currentTimeMillis() + "-" + file.getOriginalFilename()).toString();
            try {
                s3Client.putObject(
                        PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(key)
                                .contentType("image/jpeg")
                                .build(),
                        RequestBody.fromInputStream(file.getInputStream(), file.getSize())
                );

                GetObjectRequest getObjectRequest =
                        GetObjectRequest.builder()
                                .bucket(bucketName)
                                .key(key)
                                .build();

                GetObjectPresignRequest getObjectPresignRequest =
                        GetObjectPresignRequest.builder()
                                .signatureDuration(Duration.ofMinutes(10))
                                .getObjectRequest(getObjectRequest)
                                .build();

                PresignedGetObjectRequest presignedGetObjectRequest =
                        presigner.presignGetObject(getObjectPresignRequest);

                urls.add(prefixUrlCloudFront + "/" +key);

            } catch (S3Exception | IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to upload file", e);
            }
        }
        presigner.close();
        return urls;

    }

//    @Override
//    public String uploadFile(String fileName, BufferedImage image) throws IOException {
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        ImageIO.write(image, "png", os);
//        InputStream is = new ByteArrayInputStream(os.toByteArray());
//
//        s3Client.putObject(new PutObjectRequest(bucketName, fileName, is, null)
//                .withCannedAcl());
//
//        return prefixUrlCloudFront + "/" + fileName;
//    }

}
