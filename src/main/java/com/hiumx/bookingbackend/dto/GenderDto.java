package com.hiumx.bookingbackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GenderDto {
    private Long id;
    private String name;

    public GenderDto(Long id) {
        this.id = id;
    }
}
