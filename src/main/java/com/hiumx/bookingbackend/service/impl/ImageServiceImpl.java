package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.document.HotelDocument;
import com.hiumx.bookingbackend.document.ImageDocument;
import com.hiumx.bookingbackend.dto.request.ImageRequest;
import com.hiumx.bookingbackend.dto.response.ImageResponse;
import com.hiumx.bookingbackend.entity.Hotel;
import com.hiumx.bookingbackend.entity.Image;
import com.hiumx.bookingbackend.entity.Room;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.mapper.ConvenientMapper;
import com.hiumx.bookingbackend.mapper.ImageMapper;
import com.hiumx.bookingbackend.mapper.RoomMapper;
import com.hiumx.bookingbackend.mapper.TypeHotelMapper;
import com.hiumx.bookingbackend.repository.HotelRepository;
import com.hiumx.bookingbackend.repository.ImageRepository;
import com.hiumx.bookingbackend.repository.RoomRepository;
import com.hiumx.bookingbackend.repository.document.HotelDocumentRepository;
import com.hiumx.bookingbackend.repository.document.ImageDocumentRepository;
import com.hiumx.bookingbackend.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;
    private HotelRepository hotelRepository;
    private ImageDocumentRepository imageDocumentRepository;
    private HotelDocumentRepository hotelDocumentRepository;
    private RoomRepository roomRepository;

    @Override
    public List<ImageResponse> upload(ImageRequest request) {
        Hotel hotelFounded = hotelRepository.findById(request.getHotel_id())
                .orElseThrow(() -> new ApplicationException(ErrorCode.HOTEL_NOT_FOUND));

        List<ImageResponse> listImagesRes = new ArrayList<>();
        List<Image> images = new ArrayList<>();
        for (String url: request.getUrl()) {
            Image image = Image.builder()
                    .url(url)
                    .hotel(hotelFounded)
                    .build();
            Image imageSaved = imageRepository.save(image);
            images.add(image);
            imageDocumentRepository.save(ImageMapper.toImageDocument(imageSaved));
            listImagesRes.add(ImageMapper.toImageResponse(imageSaved));
        }

        List<Room> rooms = roomRepository.findByHotelId(hotelFounded.getId());

        hotelDocumentRepository.save(
                HotelDocument.builder()
                        .id(hotelFounded.getId())
                        .name(hotelFounded.getName())
                        .description(hotelFounded.getDescription())
                        .location(hotelFounded.getLocation())
                        .rate(hotelFounded.getRate())
                        .fromCenter(hotelFounded.getFromCenter())
                        .rooms(new HashSet<>(rooms.stream().map(RoomMapper::toRoomDocument).toList()))
                        .typeHotel(TypeHotelMapper.toTypeHotelDocument(hotelFounded.getTypeHotel()))
                        .images(images.stream().map(ImageMapper::toImageDocument).collect(Collectors.toSet()))
                        .convenients(new HashSet<>(hotelFounded.getConvenients().stream().map(ConvenientMapper::toConvenientDocument).toList()))
                        .managerId(hotelFounded.getManagerId().getId())
                        .build()
        );
//        hotelDocumentRepository.save(
//                HotelDocument.builder()
//                        .id(hotelFounded.getId())
//                        .images(images.stream().map(ImageMapper::toImageDocument).collect(Collectors.toSet()))
//                        .build()
//        );
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
