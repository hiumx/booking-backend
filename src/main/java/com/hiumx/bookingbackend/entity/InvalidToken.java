package com.hiumx.bookingbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invalid_tokens")
@Builder
@Data
public class InvalidToken {
    @Id
    private String id;

    @Column(name = "expire_time")
    private Date expireTime;
}
