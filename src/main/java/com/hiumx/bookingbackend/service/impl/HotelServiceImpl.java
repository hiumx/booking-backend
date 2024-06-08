package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.document.HotelDocument;
import com.hiumx.bookingbackend.document.ReviewDocument;
import com.hiumx.bookingbackend.dto.request.HotelRequest;
import com.hiumx.bookingbackend.dto.request.RoomRequest;
import com.hiumx.bookingbackend.dto.response.*;
import com.hiumx.bookingbackend.entity.*;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.mapper.*;
import com.hiumx.bookingbackend.repository.*;
import com.hiumx.bookingbackend.repository.document.HotelDocumentRepository;
import com.hiumx.bookingbackend.repository.document.ReviewDocumentRepository;
import com.hiumx.bookingbackend.service.ConvenientService;
import com.hiumx.bookingbackend.service.HotelService;
import com.hiumx.bookingbackend.service.RoomService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;
    private TypeHotelRepository typeHotelRepository;
    private ConvenientRepository convenientRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;
    private ImageRepository imageRepository;
    private ReviewRepository reviewRepository;
    private HotelDocumentRepository hotelDocumentRepository;
    private ReviewDocumentRepository reviewDocumentRepository;

    private RoomService roomService;

    @Override
    public HotelResponse create(HotelRequest request) {
        Hotel hotel = HotelMapper.toHotel(request);

        TypeHotel typeHotelFounded = typeHotelRepository.findById(request.getTypeId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.TYPE_HOTEL_NOT_FOUND));
        hotel.setTypeHotel(typeHotelFounded);

        User user = userRepository.findById(request.getManagerId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
        hotel.setManagerId(user);

        List<Convenient> convenients = convenientRepository.findAllById(request.getConvenientIds());
        hotel.setConvenients(new HashSet<>(convenients));

        Hotel hotelSaved = hotelRepository.save(hotel);

        List<RoomCreationResponse> roomResponses = new ArrayList<>();
        for (RoomRequest r: request.getRooms()) {
            Room room = RoomMapper.toRoom(r);
            room.setHotel(hotelSaved);
            roomResponses.add(RoomMapper.toRoomCreationResponse(roomRepository.save(room)));
        }

        HotelResponse hotelResponse = HotelMapper.toHotelResponse(hotelSaved);
        hotelResponse.setTypeHotel(TypeHotelMapper.toTypeHotelResponse(typeHotelFounded));
        hotelResponse.setConvenients(
            new HashSet<>(convenients.stream().map(ConvenientMapper::toConvenientResponse).toList())
        );
        hotelResponse.setRooms(new HashSet<>(roomResponses));
        hotelResponse.setManager(UserMapper.toUserResponse(user));

        return hotelResponse;
    }

    @Override
    public List<HotelGetAllResponse> getAll() {
        List<Hotel> hotels = hotelRepository.findAll();
        for (Hotel h: hotels) {
            List<Image> imagesFounded = imageRepository.findByHotelId(h.getId());
            System.out.println(imagesFounded);
            List<Room> rooms = roomRepository.findByHotelId(h.getId());
            List<Review> reviews = reviewRepository.findByHotelId(h.getId());
            hotelDocumentRepository.save(
                    HotelDocument.builder()
                            .id(h.getId())
                            .name(h.getName())
                            .description(h.getDescription())
                            .location(h.getLocation())
                            .rate(h.getRate())
                            .fromCenter(h.getFromCenter())
                            .rooms(new HashSet<>(rooms.stream().map(RoomMapper::toRoomDocument).toList()))
                            .typeHotel(TypeHotelMapper.toTypeHotelDocument(h.getTypeHotel()))
                            .convenients(new HashSet<>(h.getConvenients().stream().map(ConvenientMapper::toConvenientDocument).toList()))
                            .reviews(new HashSet<>(reviews.stream().map(ReviewMapper::toReviewDocument).toList()))
                            .images(new HashSet<>(imagesFounded.stream().map(ImageMapper::toImageDocument).toList()))
                            .managerId(h.getManagerId().getId())
                            .build()
            );
        }

        List<HotelGetAllResponse> hotelResponses = hotels.stream().map(HotelMapper::toHotelGetAllResponse).toList();
        for(HotelGetAllResponse hotelRes: hotelResponses) {
            List<Image> imagesFounded = imageRepository.findByHotelId(hotelRes.getId());
            hotelRes.setImagesResponse(imagesFounded.stream().map(ImageMapper::toImageResponse).toList());
        }

        return hotelResponses;
    }

    @Override
    public HotelResponse getById(Long id) {
//        Hotel hotelFounded = hotelRepository.findById(id)
//                .orElseThrow(() -> new ApplicationException(ErrorCode.HOTEL_NOT_FOUND));
//
//
//        List<Image> imagesFounded = imageRepository.findByHotelId(hotelFounded.getId());
//        HotelResponse hotelResponse = HotelMapper.toHotelResponse(hotelFounded);
//
//        hotelResponse.setImagesResponse(new HashSet<>(
//                imagesFounded.stream().map(ImageMapper::toImageResponse).toList()
//        ));
//
//        hotelResponse.setConvenientsResponse(new HashSet<>(
//                hotelFounded.getConvenients().stream().map(ConvenientMapper::toConvenientResponse).toList()
//        ));

        var hotelFounded = hotelDocumentRepository.findById(id);
        HotelDocument hotel = hotelFounded.get();

        Set<ReviewDocument> reviews = reviewDocumentRepository.findByHotelId(hotel.getId());

        HotelResponse hotelResponse = HotelMapper.toHotelResponseFromDocument(hotelFounded.get());

        Set<ReviewResponse> reviewResponses = new HashSet<>();

        for (Iterator<ReviewDocument> it = reviews.iterator(); it.hasNext(); ) {
            ReviewDocument review = it.next();
            User user = userRepository.findById(review.getUserId())
                    .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
            ReviewResponse reviewResponse = ReviewMapper.toReviewResponse(review);
//            reviewResponse.setHotelId(hotel.getId());
            reviewResponse.setUser(UserMapper.toUserReviewResponse(user));
            reviewResponses.add(reviewResponse);
        }

        hotelResponse.setReviews(reviewResponses);

        return hotelResponse;
    }


    @Override
    public List<HotelDocument> getByLocation(String location) {
        return hotelDocumentRepository.findByLocation(location);
    }

}
