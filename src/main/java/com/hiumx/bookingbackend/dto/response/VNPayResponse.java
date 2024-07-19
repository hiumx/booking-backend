package com.hiumx.bookingbackend.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VNPayResponse {
        public String code;
        public String message;
        public String paymentUrl;
}
