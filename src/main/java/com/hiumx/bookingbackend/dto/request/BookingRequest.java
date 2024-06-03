package com.hiumx.bookingbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookingRequest {

    private Long userId;
    private Set<Long> roomsId;
    private Integer numberAdult;
    private Integer numberChildren;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long paymentCardId;

}
