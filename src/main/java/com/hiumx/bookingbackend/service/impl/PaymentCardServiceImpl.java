
package com.hiumx.bookingbackend.service.impl;

import com.hiumx.bookingbackend.dto.request.PaymentCardRequest;
import com.hiumx.bookingbackend.dto.response.PaymentCardResponse;
import com.hiumx.bookingbackend.entity.PaymentCard;
import com.hiumx.bookingbackend.entity.User;
import com.hiumx.bookingbackend.enums.ErrorCode;
import com.hiumx.bookingbackend.exception.ApplicationException;
import com.hiumx.bookingbackend.mapper.PaymentCardMapper;
import com.hiumx.bookingbackend.repository.PaymentCardRepository;
import com.hiumx.bookingbackend.repository.UserRepository;
import com.hiumx.bookingbackend.repository.document.PaymentCardDocumentRepository;
import com.hiumx.bookingbackend.service.PaymentCardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentCardServiceImpl implements PaymentCardService {

    private UserRepository userRepository;
    private PaymentCardRepository paymentCardRepository;
    private PaymentCardDocumentRepository paymentCardDocumentRepository;

    @Override
    public PaymentCardResponse create(PaymentCardRequest request) {
        User userFounded = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_NOT_FOUND));

        PaymentCard paymentCard = PaymentCardMapper.toPaymentCard(request);
        paymentCard.setUser(userFounded);

        PaymentCard paymentCardSaved = paymentCardRepository.save(paymentCard);
        paymentCardDocumentRepository.save(PaymentCardMapper.toPaymentCardDocument(paymentCardSaved));

        return PaymentCardMapper.toPaymentCardResponse(paymentCardSaved);
    }
}
