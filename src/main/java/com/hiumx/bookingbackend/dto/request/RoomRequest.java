package com.hiumx.bookingbackend.dto.request;

import com.hiumx.bookingbackend.entity.Hotel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RoomRequest {
    private String name;
    private Long hotelId;
    private Integer numberBed;
    private Long price;
}
