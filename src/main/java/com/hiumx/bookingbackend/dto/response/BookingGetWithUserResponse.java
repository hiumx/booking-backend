package com.hiumx.bookingbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookingGetWithUserResponse {
    private Long id;

    private UserCreationResponse user;

    private List<RoomGetResponse> roomResponses;

    private Integer numberAdult;

    private Integer numberChildren;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long paymentCardId;
}
