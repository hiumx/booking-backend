package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.request.BookingRequest;
import com.hiumx.bookingbackend.dto.response.BookingResponse;
import com.hiumx.bookingbackend.entity.Booking;
import com.hiumx.bookingbackend.entity.PaymentCard;
import com.hiumx.bookingbackend.entity.Room;
import com.hiumx.bookingbackend.entity.User;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.mapper.BookingMapper;
import com.hiumx.bookingbackend.repository.BookingRepository;
import com.hiumx.bookingbackend.repository.PaymentCardRepository;
import com.hiumx.bookingbackend.repository.RoomRepository;
import com.hiumx.bookingbackend.repository.UserRepository;
import com.hiumx.bookingbackend.repository.document.BookingDocumentRepository;
import com.hiumx.bookingbackend.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    private RoomRepository roomRepository;
    private PaymentCardRepository paymentCardRepository;
    private BookingDocumentRepository bookingDocumentRepository;

    @Override
    public BookingResponse create(BookingRequest request) {
        User userFounded = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        PaymentCard paymentCardFounded = paymentCardRepository.findById(request.getPaymentCardId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.PAYMENT_CARD_NOT_FOUND));

        Set<Room> rooms = new HashSet<>();
        request.getRoomsId().stream().forEach(
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

        Booking bookingSaved = bookingRepository.save(booking);

        bookingDocumentRepository.save(BookingMapper.toBookingDocument(bookingSaved));

        return BookingMapper.toBookingResponse(bookingSaved);
    }


}
