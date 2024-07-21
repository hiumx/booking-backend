package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.service.WebhookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class WebhookServiceImpl implements WebhookService {
    private RestTemplate restTemplate;

    public void handlePaymentResponse(String paymentStatus) {
        if("00".equals(paymentStatus)) {
            System.out.println("Payment successfully.");
//            sendWebhookToClient(paymentStatus);
        } else {
            System.out.println("Payment failure");
        }
    }

    private void sendWebhookToClient(String paymentData) {
        String clientWebhookUrl = "http://localhost:3000/payment/vn-pay-call-back";

        try {
            restTemplate.postForEntity(clientWebhookUrl, paymentData, String.class);
            System.out.println("Successfully sent webhook to client");
        } catch (Exception e) {
            System.err.println("Error sending webhook to client: " + e.getMessage());
        }
    }
}
