package com.hiumx.bookingbackend.dto.request;


import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RoleRequest {
    private String name;
    private String description;
    private Set<Long> permissions;
}
