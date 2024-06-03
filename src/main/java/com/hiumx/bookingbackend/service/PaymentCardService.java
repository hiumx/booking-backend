package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.PaymentCardRequest;
import com.hiumx.bookingbackend.dto.response.PaymentCardResponse;

public interface PaymentCardService {
    PaymentCardResponse create(PaymentCardRequest request);
}
