package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.document.ImageDocument;
import com.hiumx.bookingbackend.dto.request.ImageRequest;
import com.hiumx.bookingbackend.dto.response.ImageResponse;
import com.hiumx.bookingbackend.entity.Hotel;
import com.hiumx.bookingbackend.entity.Image;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.mapper.ImageMapper;
import com.hiumx.bookingbackend.repository.HotelRepository;
import com.hiumx.bookingbackend.repository.ImageRepository;
import com.hiumx.bookingbackend.repository.document.ImageDocumentRepository;
import com.hiumx.bookingbackend.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;
    private HotelRepository hotelRepository;
    private ImageDocumentRepository imageDocumentRepository;

    @Override
    public List<ImageResponse> upload(ImageRequest request) {
        Hotel hotelFounded = hotelRepository.findById(request.getHotel_id())
                .orElseThrow(() -> new ApplicationException(ErrorCode.HOTEL_NOT_FOUND));

        List<ImageResponse> listImagesRes = new ArrayList<>();
        for (String url: request.getUrl()) {
            Image image = Image.builder()
                    .url(url)
                    .hotel(hotelFounded)
                    .build();
            listImagesRes.add(ImageMapper.toImageResponse(imageRepository.save(image)));
        }
        return listImagesRes;
    }

    @Override
    public List<ImageResponse> findByHotelId(Long hotelId) {
        hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.HOTEL_NOT_FOUND));
        List<Image> images = imageRepository.findByHotelId(hotelId);
        return images.stream().map(ImageMapper::toImageResponse).toList();
    }

    @Override
    public List<ImageResponse> getAll() {
        List<Image> images = imageRepository.findAll();
//        for (Image i : images) {
//            imageDocumentRepository.save(
//                    ImageDocument.builder()
//                    .id(i.getId())
//                    .url(i.getUrl())
//                    .hotelId(i.getHotel().getId())
//                    .build()
//            );
//        }
        return images.stream().map(ImageMapper::toImageResponse).toList();
    }
}
