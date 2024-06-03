package com.hiumx.bookingbackend.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "convenients")
@Builder
@Data
public class Convenient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

//    @ManyToMany(mappedBy = "convenients")
//    private Set<Hotel> hotels;
}
