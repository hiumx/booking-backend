package com.hiumx.bookingbackend.controller;

import com.hiumx.bookingbackend.dto.response.ApiResponse;
import com.hiumx.bookingbackend.dto.response.VNPayResponse;
import com.hiumx.bookingbackend.service.PaymentService;
import com.hiumx.bookingbackend.service.SocketIOService;
import com.hiumx.bookingbackend.service.WebhookService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class PaymentController {

    private final PaymentService paymentService;
    private final WebhookService webhookService;
//    private final RestTemplate restTemplate;
    private final SocketIOService socketIOService;

    @GetMapping("/vn-pay")
    public ApiResponse<?> pay(HttpServletRequest request) {
        return ApiResponse.builder()
                .code(1000)
                .message("Success")
                .metadata(paymentService.createVnPayPayment(request).getPaymentUrl())
                .build();
    }

    @GetMapping("/vn-pay-callback")
    public ApiResponse<?> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");

        Long amount = 0L;
        try {
            amount = Long.parseLong(request.getParameter("vnp_Amount"));
        } catch (NumberFormatException e) {
            throw e;
        }

        if (status.equals("00")) {
            webhookService.handlePaymentResponse(status);
            socketIOService.sendPaymentResponse("1", "Success");
            return ApiResponse.builder().code(1000).message("Success").metadata(amount).build();


        } else {
            return ApiResponse.builder().code(1111).message("Failed!").build();
        }
    }
}