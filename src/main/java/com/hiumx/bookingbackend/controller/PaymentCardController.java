package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.request.PaymentCardRequest;
import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.PaymentCardResponse;
import com.hiumx.bookingbackend.service.PaymentCardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/api/v1/payment_cards")
public class PaymentCardController {

    private PaymentCardService paymentCardService;

    @PostMapping
    public ApiResponse<?> create(@RequestBody PaymentCardRequest request) {
        PaymentCardResponse paymentCardResponse = paymentCardService.create(request);
        return ApiResponse.builder()
                .code(1000)
                .message("Create payment card successfully")
                .metadata(paymentCardResponse)
                .build();
    }
}
