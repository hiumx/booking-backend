package com.hiumx.bookingbackend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SearchFilterRequest {
    private String name;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private OptionSearchRequest options;
    private List<Long> checksType;
    private List<Long> checksConvenient;
    private List<Long> checksRating;
    private Long lowestPrice = 0L;
    private Long highestPrice = 1000L;
}
