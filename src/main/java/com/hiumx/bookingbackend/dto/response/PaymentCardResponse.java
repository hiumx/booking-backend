package com.hiumx.bookingbackend.dto.response;

import com.hiumx.bookingbackend.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PaymentCardResponse {
    private Long id;

    private String numberCard;

    private UserCreationResponse user;

    private Set<Booking> bookings;
}
