package com.hiumx.bookingbackend.mapper;

import com.hiumx.bookingbackend.document.PaymentCardDocument;
import com.hiumx.bookingbackend.dto.request.PaymentCardRequest;
import com.hiumx.bookingbackend.dto.response.PaymentCardResponse;
import com.hiumx.bookingbackend.entity.Booking;
import com.hiumx.bookingbackend.entity.PaymentCard;

import java.util.HashSet;

public class PaymentCardMapper {
    public static PaymentCard toPaymentCard(PaymentCardRequest request) {
        return PaymentCard.builder()
                .numberCard(request.getNumberCard())
                .build();
    }

    public static PaymentCardDocument toPaymentCardDocument(PaymentCard paymentCard) {
        return PaymentCardDocument.builder()
                .id(paymentCard.getId())
                .cardNumber(paymentCard.getNumberCard())
                .user(paymentCard.getUser())
                .build();
    }

    public static PaymentCardResponse toPaymentCardResponse(PaymentCard paymentCard) {
        return PaymentCardResponse.builder()
                .id(paymentCard.getId())
                .numberCard(paymentCard.getNumberCard())
                .user(UserMapper.toUserResponse(paymentCard.getUser()))
                .build();
    }
}
