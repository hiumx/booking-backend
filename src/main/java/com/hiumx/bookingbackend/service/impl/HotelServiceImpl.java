package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.request.HotelRequest;
import com.hiumx.bookingbackend.dto.request.RoomRequest;
import com.hiumx.bookingbackend.dto.response.HotelGetAllResponse;
import com.hiumx.bookingbackend.dto.response.HotelResponse;
import com.hiumx.bookingbackend.dto.response.RoomCreationResponse;
import com.hiumx.bookingbackend.entity.*;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.mapper.*;
import com.hiumx.bookingbackend.repository.*;
import com.hiumx.bookingbackend.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {

    private HotelRepository hotelRepository;
    private TypeHotelRepository typeHotelRepository;
    private ConvenientRepository convenientRepository;
    private RoomRepository roomRepository;
    private UserRepository userRepository;
    private ImageRepository imageRepository;

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
        hotelResponse.setTypeHotelResponse(TypeHotelMapper.toTypeHotelResponse(typeHotelFounded));
        hotelResponse.setConvenientsResponse(
            new HashSet<>(convenients.stream().map(ConvenientMapper::toConvenientResponse).toList())
        );
        hotelResponse.setRoomResponses(new HashSet<>(roomResponses));
        hotelResponse.setManager(UserMapper.toUserResponse(user));

        return hotelResponse;
    }

    @Override
    public List<HotelGetAllResponse> getAll() {
        List<Hotel> hotels = hotelRepository.findAll();

        List<HotelGetAllResponse> hotelResponses = hotels.stream().map(HotelMapper::toHotelGetAllResponse).toList();
        for(HotelGetAllResponse hotelRes: hotelResponses) {
            List<Image> imagesFounded = imageRepository.findByHotelId(hotelRes.getId());
            hotelRes.setImagesResponse(imagesFounded.stream().map(ImageMapper::toImageResponse).toList());
        }

        return hotelResponses;
    }

    @Override
    public HotelResponse getById(Long id) {
        Hotel hotelFounded = hotelRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.HOTEL_NOT_FOUND));
        System.out.println(hotelFounded);

        List<Image> imagesFounded = imageRepository.findByHotelId(hotelFounded.getId());
        HotelResponse hotelResponse = HotelMapper.toHotelResponse(hotelFounded);

        hotelResponse.setImagesResponse(new HashSet<>(
                imagesFounded.stream().map(ImageMapper::toImageResponse).toList()
        ));

        hotelResponse.setConvenientsResponse(new HashSet<>(
                hotelFounded.getConvenients().stream().map(ConvenientMapper::toConvenientResponse).toList()
        ));

        return  hotelResponse;
    }
}
