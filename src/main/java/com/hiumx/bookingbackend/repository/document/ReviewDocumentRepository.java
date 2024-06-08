package com.hiumx.bookingbackend.repository.document;

import com.hiumx.bookingbackend.document.ReviewDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Set;

public interface ReviewDocumentRepository extends ElasticsearchRepository<ReviewDocument, Long> {
    Set<ReviewDocument> findByHotelId(Long id);
}
