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
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        String date = request.getParameter("vnp_PayDate");
        String numberRef = request.getParameter("vnp_BankTranNo");

        Long amount = 0L;
        try {
            amount = Long.parseLong(request.getParameter("vnp_Amount"));
        } catch (NumberFormatException e) {
            throw e;
        }

        RedirectView redirectView = new RedirectView();

        if (status.equals("00")) {
            // Payment successful, redirect to success page
            redirectView.setUrl("http://localhost:3000/payment/vn-pay-call-back?amount="
                    + amount + "&date=" + date + "&numberRef=" + numberRef);
        } else {
            // Payment failed, redirect to failure page
            redirectView.setUrl("http://localhost:3000/failure");
        }

        return redirectView;
    }
}