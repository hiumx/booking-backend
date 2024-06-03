package com.hiumx.bookingbackend.repository.document;

import com.hiumx.bookingbackend.document.ImageDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ImageDocumentRepository extends ElasticsearchRepository<ImageDocument, Long> {
}
