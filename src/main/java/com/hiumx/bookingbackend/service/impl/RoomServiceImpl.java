package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.document.HotelDocument;
import com.hiumx.bookingbackend.document.RoomDocument;
import com.hiumx.bookingbackend.dto.request.RoomRequest;
import com.hiumx.bookingbackend.dto.response.RoomGetResponse;
import com.hiumx.bookingbackend.dto.response.RoomResponse;
import com.hiumx.bookingbackend.entity.Hotel;
import com.hiumx.bookingbackend.entity.Room;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.mapper.HotelMapper;
import com.hiumx.bookingbackend.mapper.RoomMapper;
import com.hiumx.bookingbackend.repository.HotelRepository;
import com.hiumx.bookingbackend.repository.RoomRepository;
import com.hiumx.bookingbackend.repository.document.HotelDocumentRepository;
import com.hiumx.bookingbackend.repository.document.RoomDocumentRepository;
import com.hiumx.bookingbackend.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;
    private HotelRepository hotelRepository;
    private RoomDocumentRepository roomDocumentRepository;
    private final HotelDocumentRepository hotelDocumentRepository;


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

    @Override
    public RoomResponse getRoomById(Long id) {
        RoomDocument room = roomDocumentRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.ROOM_NOT_FOUND));

        HotelDocument hotel = hotelDocumentRepository.findById(room.getHotelId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.HOTEL_NOT_FOUND));

        RoomResponse response = RoomMapper.toRoomResponse(room);
        response.setHotel(HotelMapper.toHotelResponseFromDocument(hotel));
        return response;
    }

    @Override
    public List<RoomGetResponse> getAll() {
        List<Room> rooms = roomRepository.findAll();
//        for(Room r : rooms) {
//            roomDocumentRepository.save(
//                    RoomDocument.builder()
//                            .id(r.getId())
//                            .name(r.getName())
//                            .numberBed(r.getNumberBed())
//                            .price(r.getPrice())
//                            .hotelId(r.getHotel().getId())
//                            .build()
//            );
//        }
        return rooms.stream().map(RoomMapper::toRoomGetResponse).toList();
    }
}
