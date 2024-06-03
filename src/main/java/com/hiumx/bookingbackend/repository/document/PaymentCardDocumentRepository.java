package com.hiumx.bookingbackend.repository.document;

import com.hiumx.bookingbackend.document.PaymentCardDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PaymentCardDocumentRepository extends ElasticsearchRepository<PaymentCardDocument, Long> {
}
