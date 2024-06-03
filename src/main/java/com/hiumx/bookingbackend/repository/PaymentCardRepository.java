package com.hiumx.bookingbackend.repository;

import com.hiumx.bookingbackend.entity.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {

}
