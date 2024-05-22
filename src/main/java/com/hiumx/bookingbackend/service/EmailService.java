package com.hiumx.bookingbackend.service;

import com.hiumx.bookingbackend.dto.request.EmailRequest;

public interface EmailService {
    void sendSimpleEmail(EmailRequest request);
}
