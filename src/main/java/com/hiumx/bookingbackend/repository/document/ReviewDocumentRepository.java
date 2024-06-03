package com.hiumx.bookingbackend.repository.document;

import com.hiumx.bookingbackend.document.ReviewDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ReviewDocumentRepository extends ElasticsearchRepository<ReviewDocument, Long> {
}
