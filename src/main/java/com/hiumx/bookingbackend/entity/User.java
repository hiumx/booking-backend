package com.hiumx.bookingbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@Builder
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(name = "date_of_birth")
    private LocalDate dob;

    @ManyToOne
    @JoinColumn(name = "gender_id", columnDefinition = "LONG")
    private Gender gender;
    private String address;
    private String image;

    @ManyToMany
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews;

    @Column(name = "is_active")
    private Integer isActive;
}
