package com.hiumx.bookingbackend.repository.document;

import com.hiumx.bookingbackend.document.RoomDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface RoomDocumentRepository extends ElasticsearchRepository<RoomDocument, Long> {
    List<RoomDocument> findByHotelId(Long hotelId);
}
