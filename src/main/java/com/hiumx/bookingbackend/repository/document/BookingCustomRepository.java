package com.hiumx.bookingbackend.repository.document;

import com.hiumx.bookingbackend.document.BookingDocument;
import com.hiumx.bookingbackend.document.HotelDocument;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.erhlc.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class BookingCustomRepository {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public boolean isBookingDateRangeOverlap(Long roomId, LocalDate startDate, LocalDate endDate) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                        .must(QueryBuilders.termQuery("room_id", roomId))
                        .must(QueryBuilders.rangeQuery("start_date").lte(endDate))
                        .must(QueryBuilders.rangeQuery("end_date").gte(startDate));

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery).build();

        SearchHits<BookingDocument> searchHits = elasticsearchRestTemplate.search(searchQuery, BookingDocument.class);
        return searchHits.hasSearchHits();
    }

    public List<BookingDocument> getBookingByUser(Long userId) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("user_id", userId))
                .must(QueryBuilders.rangeQuery("start_date").gte(LocalDate.now()));


        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery).build();

        return elasticsearchRestTemplate.search(searchQuery, BookingDocument.class)
                .stream().map(SearchHit::getContent)
                .toList();
    }

    public List<BookingDocument> getBookingByUserHotel(Long userId, Long hotelId) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("user_id", userId))
                .must(QueryBuilders.matchQuery("hotel_id", hotelId));


        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery).build();

        return elasticsearchRestTemplate.search(searchQuery, BookingDocument.class)
                .stream().map(SearchHit::getContent)
                .toList();
    }

    public List<BookingDocument> getBookingByHotel(Long hotelId) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("hotel_id", hotelId))
                .must(QueryBuilders.rangeQuery("start_date").gte(LocalDate.now()));


        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery).build();

        return elasticsearchRestTemplate.search(searchQuery, BookingDocument.class)
                .stream().map(SearchHit::getContent)
                .toList();
    }
}
