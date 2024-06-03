package com.hiumx.bookingbackend.repository.document;

import com.hiumx.bookingbackend.document.ConvenientDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ConvenientDocumentRepository extends ElasticsearchRepository<ConvenientDocument, Long> {
}
