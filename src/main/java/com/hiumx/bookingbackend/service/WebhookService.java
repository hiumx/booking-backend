package com.hiumx.bookingbackend.service;

public interface WebhookService {
    public void handlePaymentResponse(String paymentStatus);
}
