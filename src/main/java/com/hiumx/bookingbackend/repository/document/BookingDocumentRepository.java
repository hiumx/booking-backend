package com.hiumx.bookingbackend.repository.document;

import com.hiumx.bookingbackend.document.BookingDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookingDocumentRepository extends ElasticsearchRepository<BookingDocument, Long> {
}
