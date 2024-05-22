package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.EmailRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/emails")
@CrossOrigin(value = "http://localhost:3000")
@AllArgsConstructor
public class EmailController {

    private EmailService emailService;

    @PostMapping("/send-mail")
    ApiResponse<?> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendSimpleEmail(request);
        return ApiResponse.builder()
                .code(1000)
                .metadata("Send mail successfully")
                .build();
    }
}
