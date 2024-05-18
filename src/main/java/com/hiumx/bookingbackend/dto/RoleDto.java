package com.hiumx.bookingbackend.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RoleDto {
    private Long id;
    private String name;
    private String description;
}
