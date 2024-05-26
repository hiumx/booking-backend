package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.request.RoomRequest;
import com.hiumx.bookingbackend.dto.response.RoomGetResponse;
import com.hiumx.bookingbackend.dto.response.RoomResponse;
import com.hiumx.bookingbackend.entity.Hotel;
import com.hiumx.bookingbackend.entity.Room;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.mapper.RoomMapper;
import com.hiumx.bookingbackend.repository.HotelRepository;
import com.hiumx.bookingbackend.repository.RoomRepository;
import com.hiumx.bookingbackend.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    private HotelRepository hotelRepository;


    @Override
    public RoomResponse create(RoomRequest request) {
        Hotel hotelFounded = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.HOTEL_NOT_FOUND));

        Room room = RoomMapper.toRoom(request);
        room.setHotel(hotelFounded);

        return RoomMapper.toRoomResponse(roomRepository.save(room));
    }

    @Override
    public List<RoomGetResponse> getRoomsByHotelId(Long hotelId) {
        hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.HOTEL_NOT_FOUND));

        List<Room> rooms = roomRepository.findByHotelId(hotelId);

        return rooms.stream().map(RoomMapper::toRoomGetResponse).toList();
    }
}
