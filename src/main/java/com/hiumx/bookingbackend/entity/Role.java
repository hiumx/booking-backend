package com.hiumx.bookingbackend.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
@Builder
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToMany
    private Set<Permission> permissions;
    public Role(Long id) {
        this.id = id;
    }
}
