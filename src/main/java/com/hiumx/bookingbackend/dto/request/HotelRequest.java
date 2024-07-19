package com.hiumx.bookingbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HotelRequest {
    private String name;
    private String description;
    private Long typeId;
    private String location;
    private Float rate;
    private Float fromCenter;
    Set<Long> convenientIds;
    Set<RoomRequest> rooms;
    private Long managerId;
}
