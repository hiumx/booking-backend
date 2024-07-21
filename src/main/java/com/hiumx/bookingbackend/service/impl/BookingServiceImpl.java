package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.document.BookingDocument;
import com.hiumx.bookingbackend.dto.request.BookingRequest;
import com.hiumx.bookingbackend.dto.response.BookingGetResponse;
import com.hiumx.bookingbackend.dto.response.BookingGetWithUserResponse;
import com.hiumx.bookingbackend.dto.response.BookingResponse;
import com.hiumx.bookingbackend.entity.*;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.mapper.BookingMapper;
import com.hiumx.bookingbackend.mapper.RoomMapper;
import com.hiumx.bookingbackend.mapper.UserMapper;
import com.hiumx.bookingbackend.repository.*;
import com.hiumx.bookingbackend.repository.document.BookingCustomRepository;
import com.hiumx.bookingbackend.repository.document.BookingDocumentRepository;
import com.hiumx.bookingbackend.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    private RoomRepository roomRepository;
    private PaymentCardRepository paymentCardRepository;
    private BookingDocumentRepository bookingDocumentRepository;
    private BookingCustomRepository bookingCustomRepository;
    private HotelRepository hotelRepository;

    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public BookingResponse create(BookingRequest request) {
        User userFounded = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        PaymentCard paymentCardFounded = paymentCardRepository.findById(request.getPaymentCardId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.PAYMENT_CARD_NOT_FOUND));

        Hotel hotel = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.HOTEL_NOT_FOUND));

        Set<Room> rooms = new HashSet<>();
        request.getRoomsId().forEach(
                id -> {
                    Room roomFounded = roomRepository.findById(id)
                            .orElseThrow(() -> new ApplicationException(ErrorCode.ROOM_NOT_FOUND));
                    rooms.add(roomFounded);
                }
        );

        Booking booking = BookingMapper.toBooking(request);
        booking.setUser(userFounded);
        booking.setPaymentCard(paymentCardFounded);
        booking.setRoom(rooms);
        booking.setHotel(hotel);


        Booking bookingSaved = bookingRepository.save(booking);

        kafkaTemplate.send("booking-success", "Congratulation for your booking successfully");

        bookingDocumentRepository.save(BookingMapper.toBookingDocument(bookingSaved));

        return BookingMapper.toBookingResponse(bookingSaved);
    }

    @Override
    public List<BookingGetResponse> getBookingByUserId(Long id) {
        List<BookingDocument> bookingDocuments = bookingCustomRepository.getBookingByUser(id);
        List<BookingGetResponse> res = bookingDocuments.stream().map(BookingMapper::toBookingResponseFromDocument).toList();

        for(int i = 0; i < bookingDocuments.size(); i++) {
            List<Room> rooms = roomRepository.findAllById(bookingDocuments.get(i).getRoomsId());
            res.get(i).setRoomResponses(rooms.stream().map(RoomMapper::toRoomGetResponse).toList());
        }
        return res;
    }

    @Override
    public List<BookingGetWithUserResponse> getBookingByHotelId(Long id) {
        List<BookingDocument> bookingDocuments = bookingCustomRepository.getBookingByHotel(id);
        List<BookingGetWithUserResponse> res = bookingDocuments.stream().map(BookingMapper::toBookingWithUserResponseFromDocument).toList();



        for(int i = 0; i < bookingDocuments.size(); i++) {
            List<Room> rooms = roomRepository.findAllById(bookingDocuments.get(i).getRoomsId());
            res.get(i).setRoomResponses(rooms.stream().map(RoomMapper::toRoomGetResponse).toList());
            User user = userRepository.findById(bookingDocuments.get(i).getUserId())
                    .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));
            res.get(i).setUser(UserMapper.toUserResponse(user));
        }
        return res;
    }

    @Override
    public BookingResponse deleteBookingById(Long id) {
        Booking bookingFounded = bookingRepository.findById(id)
                        .orElseThrow(() -> new ApplicationException(ErrorCode.BOOKING_NOT_FOUND));

        bookingRepository.deleteById(id);
        bookingDocumentRepository.deleteById(id);
        kafkaTemplate.send("booking-success", "Cancel booking successfully");

        return BookingMapper.toBookingResponse(bookingFounded);
    }


}
