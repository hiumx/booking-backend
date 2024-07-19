package com.hiumx.bookingbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookingGetResponse {
    private Long id;

    private Long userId;

    private List<RoomGetResponse> roomResponses;

    private Integer numberAdult;

    private Integer numberChildren;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long paymentCardId;
}
