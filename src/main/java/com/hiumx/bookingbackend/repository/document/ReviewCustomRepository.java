package com.hiumx.bookingbackend.repository.document;

import com.hiumx.bookingbackend.document.BookingDocument;
import com.hiumx.bookingbackend.document.ReviewDocument;
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
public class ReviewCustomRepository {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public boolean findReviewByUserHotel(Long userId, Long hotelId) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("user_id", userId))
                .must(QueryBuilders.matchQuery("hotel_id", hotelId));


        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery).build();

        return elasticsearchRestTemplate.search(searchQuery, ReviewDocument.class)
                .hasSearchHits();
    }

}
