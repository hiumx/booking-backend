package com.hiumx.bookingbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String phone;
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(name = "date_of_birth")
    private LocalDate dob;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;
    private String address;
    private String image;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "is_active")
    private Integer isActive;
}
