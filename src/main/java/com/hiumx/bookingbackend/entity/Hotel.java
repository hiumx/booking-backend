package com.hiumx.bookingbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotels")
@Builder
@Data
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeHotel typeHotel;
    private String location;

    @Column(name = "from_center", columnDefinition = "FLOAT")
    private Float fromCenter;
    private Float rate;

    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "hotel_convenient",
//            joinColumns = @JoinColumn(name = "hotel_id"),
//            inverseJoinColumns = @JoinColumn(name = "convenient_id")
//    )
    private Set<Convenient> convenients;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Room> rooms;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    private Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Review> reviews;

    @JsonIgnore
    @ManyToMany(mappedBy = "hotelsSaved")
    private Set<User> usersSaved = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_hotel_saved",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id")
    )
    private Set<Hotel> hotelsSaved = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonIgnore
    private User managerId;

}
