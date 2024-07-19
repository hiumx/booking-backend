package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.response.VNPayResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {
    public VNPayResponse createVnPayPayment(HttpServletRequest request);
}
