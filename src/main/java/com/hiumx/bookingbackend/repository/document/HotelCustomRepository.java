package com.hiumx.bookingbackend.repository.document;

import com.hiumx.bookingbackend.document.BookingDocument;
import com.hiumx.bookingbackend.document.HotelDocument;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
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
public class HotelCustomRepository {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public List<HotelDocument> findByTypeHotelId(Long typeHotelId, String location) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("location", location))
                .must(QueryBuilders.matchQuery("typeHotel.id", typeHotelId));

        Query query = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .build();

        return elasticsearchRestTemplate.search(query, HotelDocument.class)
                .stream().map(SearchHit::getContent)
                .toList();
    }

    public List<HotelDocument> findByConvenient(Long convenientId, String location) {
        NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery(
                "convenients",
                QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("convenients.id", convenientId))
                        .must(QueryBuilders.matchQuery("location", location)),
                ScoreMode.Avg
        );

        Query query = new NativeSearchQueryBuilder()
                .withQuery(nestedQueryBuilder)
                .build();

        return elasticsearchRestTemplate.search(query, HotelDocument.class)
                .stream().map(SearchHit::getContent)
                .toList();
    }

    public List<HotelDocument> findByTypeHotelAndConvenient(Long typeHotel, Long convenientId, String location) {
        BoolQueryBuilder queryBuilders = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("location", location))
                .must(QueryBuilders.matchQuery("typeHotel.id", typeHotel))
                .must(QueryBuilders.nestedQuery(
                        "convenients",
                        QueryBuilders.boolQuery()
                                .must(QueryBuilders.matchQuery("convenients.id", convenientId)),
                        ScoreMode.Avg
                ));
        Query query = new NativeSearchQueryBuilder()
                .withQuery(queryBuilders)
                .build();

        return elasticsearchRestTemplate.search(query, HotelDocument.class)
                .stream().map(SearchHit::getContent)
                .toList();
    }

}
