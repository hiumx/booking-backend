package com.hiumx.bookingbackend.repository.document;

import com.hiumx.bookingbackend.document.HotelDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface HotelDocumentRepository extends ElasticsearchRepository<HotelDocument, Long> {
    List<HotelDocument> findByLocation(String location);
//    HotelDocument findById(String id);
}
