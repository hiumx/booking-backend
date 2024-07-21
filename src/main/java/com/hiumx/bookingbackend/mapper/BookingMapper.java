package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.document.BookingDocument;
import com.hiumx.bookingbackend.dto.request.BookingRequest;
import com.hiumx.bookingbackend.dto.response.BookingGetResponse;
import com.hiumx.bookingbackend.dto.response.BookingGetWithUserResponse;
import com.hiumx.bookingbackend.dto.response.BookingResponse;
import com.hiumx.bookingbackend.entity.Booking;
import com.hiumx.bookingbackend.entity.Room;

import java.util.HashSet;

public class BookingMapper {

    public static Booking toBooking(BookingRequest request) {
        return Booking.builder()
                .numberAdult(request.getNumberAdult())
                .numberChildren(request.getNumberChildren())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();
    }

    public static BookingDocument toBookingDocument(Booking booking) {
        return BookingDocument.builder()
                .id(booking.getId())
                .numberAdult(booking.getNumberAdult())
                .numberChildren(booking.getNumberChildren())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .userId(booking.getUser().getId())
                .paymentCardId(booking.getPaymentCard().getId())
                .roomsId(new HashSet<>(booking.getRoom().stream().map(Room::getId).toList()))
                .hotelId(booking.getHotel().getId())
                .build();
    }

    public static BookingResponse toBookingResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .numberAdult(booking.getNumberAdult())
                .numberChildren(booking.getNumberChildren())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .paymentCardId(booking.getPaymentCard().getId())
                .userId(booking.getUser().getId())
                .roomsId(new HashSet<>(booking.getRoom().stream().map(Room::getId).toList()))
                .hotelId(booking.getHotel().getId())
                .build();
    }

    public static BookingGetResponse toBookingResponseFromDocument(BookingDocument booking) {
        return BookingGetResponse.builder()
                .id(booking.getId())
                .numberAdult(booking.getNumberAdult())
                .numberChildren(booking.getNumberChildren())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .build();
    }

    public static BookingGetWithUserResponse toBookingWithUserResponseFromDocument(BookingDocument booking) {
        return BookingGetWithUserResponse.builder()
                .id(booking.getId())
                .numberAdult(booking.getNumberAdult())
                .numberChildren(booking.getNumberChildren())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .build();
    }
}
