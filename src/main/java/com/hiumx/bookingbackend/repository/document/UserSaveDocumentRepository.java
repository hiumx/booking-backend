package com.hiumx.bookingbackend.repository.document;

import com.hiumx.bookingbackend.document.UserSaveDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserSaveDocumentRepository extends ElasticsearchRepository<UserSaveDocument, Long> {
    UserSaveDocument findByUserId(Long userId);
}
