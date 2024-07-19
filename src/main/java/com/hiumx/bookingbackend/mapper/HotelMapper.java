package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.document.HotelDocument;
import com.hiumx.bookingbackend.dto.request.HotelRequest;
import com.hiumx.bookingbackend.dto.response.HotelGetAllResponse;
import com.hiumx.bookingbackend.dto.response.HotelResponse;
import com.hiumx.bookingbackend.dto.response.HotelSearchAllResponse;
import com.hiumx.bookingbackend.entity.Hotel;
import com.hiumx.bookingbackend.entity.User;

import java.util.HashSet;
import java.util.stream.Collectors;

public class HotelMapper {
    public static Hotel toHotel(HotelRequest request) {
        return Hotel.builder()
                .name(request.getName())
                .description(request.getDescription())
                .location(request.getLocation())
                .fromCenter(request.getFromCenter())
                .rate(request.getRate())
                .build();
    }


    public static HotelResponse toHotelResponse(Hotel hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .description(hotel.getDescription())
                .location(hotel.getLocation())
                .typeHotel(TypeHotelMapper.toTypeHotelResponse(hotel.getTypeHotel()))
                .rate(hotel.getRate())
                .manager(UserMapper.toUserResponse(hotel.getManagerId()))
                .build();
    }

    public static HotelResponse toHotelResponseFromDocument(HotelDocument hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .description(hotel.getDescription())
                .location(hotel.getLocation())
                .typeHotel(TypeHotelMapper.toTypeHotelResponseFromDocument(hotel.getTypeHotel()))
                .convenients(
                        hotel.getConvenients().stream().map(ConvenientMapper::toConvenientResponseFromDocument).collect(Collectors.toSet())
                )
                .images(hotel.getImages().stream().map(ImageMapper::toImageResponseFromDocument).collect(Collectors.toSet()))
                .rooms(hotel.getRooms().stream().map(RoomMapper::toRoomCreationResponseFromDocument).collect(Collectors.toSet()))
//                .reviews(hotel.getReviews().stream().map(ReviewMapper::toReviewResponse).collect(Collectors.toSet()))
                .rate(hotel.getRate())
                .build();
    }

    public static HotelResponse toHotelSearchResponseFromDocument(HotelDocument hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .description(hotel.getDescription())
                .location(hotel.getLocation())
                .typeHotel(TypeHotelMapper.toTypeHotelResponseFromDocument(hotel.getTypeHotel()))
                .convenients(
                        hotel.getConvenients().stream().map(ConvenientMapper::toConvenientResponseFromDocument).collect(Collectors.toSet())
                )
                .images(hotel.getImages().stream().map(ImageMapper::toImageResponseFromDocument).collect(Collectors.toSet()))
//                .rooms(hotel.getRooms().stream().map(RoomMapper::toRoomCreationResponseFromDocument).collect(Collectors.toSet()))
//                .reviews(hotel.getReviews().stream().map(ReviewMapper::toReviewResponse).collect(Collectors.toSet()))
                .rate(hotel.getRate())
                .build();
    }

    public static HotelResponse toHotelResponseFromDocumentV2(HotelDocument hotel) {
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .description(hotel.getDescription())
                .location(hotel.getLocation())
                .typeHotel(TypeHotelMapper.toTypeHotelResponseFromDocument(hotel.getTypeHotel()))
                .convenients(
                        hotel.getConvenients().stream().map(ConvenientMapper::toConvenientResponseFromDocument).collect(Collectors.toSet())
                )
                .rooms(hotel.getRooms().stream().map(RoomMapper::toRoomCreationResponseFromDocument).collect(Collectors.toSet()))
                .rate(hotel.getRate())
                .build();
    }

    public static HotelGetAllResponse toHotelGetAllResponse(Hotel hotel) {
        return HotelGetAllResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .description(hotel.getDescription())
                .location(hotel.getLocation())
                .typeHotelResponse(TypeHotelMapper.toTypeHotelResponse(hotel.getTypeHotel()))
                .rate(hotel.getRate())
                .managerId(hotel.getManagerId().getId())
                .build();
    }

    public static HotelSearchAllResponse toHotelSearchAllResponse(Hotel hotel) {
        return HotelSearchAllResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .location(hotel.getLocation())
                .rate(hotel.getRate())
                .typeHotel(TypeHotelMapper.toTypeHotelResponse(hotel.getTypeHotel()))
                .fromCenter(hotel.getFromCenter())
                .build();
    }

    public static HotelSearchAllResponse toHotelSearchAllResponse(HotelDocument hotel) {
        return HotelSearchAllResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .location(hotel.getLocation())
                .rate(hotel.getRate())
                .typeHotel(TypeHotelMapper.toTypeHotelResponseFromDocument(hotel.getTypeHotel()))
                .image(ImageMapper.toImageResponseFromDocument(hotel.getImages().stream().toList().getFirst()))
                .reviews(hotel.getReviews().stream().map(ReviewMapper::toReviewGetAllHotelResponse).toList())
                .fromCenter(hotel.getFromCenter())
                .build();
    }
}
