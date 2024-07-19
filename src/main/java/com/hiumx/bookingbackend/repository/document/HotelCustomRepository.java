package com.hiumx.bookingbackend.repository.document;

import com.hiumx.bookingbackend.document.BookingDocument;
import com.hiumx.bookingbackend.document.HotelDocument;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.erhlc.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQuery;
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

    public List<HotelDocument> findByTypeHotelConvenientPrice(
            Long typeHotel, Long convenientId, String location, Long lowestPrice, Long highestPrice
    ) {
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("rooms.price")
                .gte(lowestPrice)
                .lte(highestPrice);

        BoolQueryBuilder roomBoolQuery = QueryBuilders.boolQuery()
                .must(rangeQuery);

        NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("rooms", roomBoolQuery, ScoreMode.Avg);

        MatchQueryBuilder locationQuery = QueryBuilders.matchQuery("location", location);
        MatchQueryBuilder typeHotelQuery = QueryBuilders.matchQuery("typeHotel.id", typeHotel);

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery()
                .must(nestedQuery)
                .must(locationQuery)
                .must(typeHotelQuery);

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .build();

        return elasticsearchRestTemplate.search(searchQuery, HotelDocument.class)
                .stream().map(SearchHit::getContent)
                .toList();
    }

    public List<HotelDocument> getTop10HighestRating() {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .withSort(Sort.by(Sort.Order.desc("rate")))
                .withPageable(PageRequest.of(0, 10))
                .build();

        return elasticsearchRestTemplate.search(searchQuery, HotelDocument.class)
                .stream().map(SearchHit::getContent)
                .toList();
    }

    public List<HotelDocument> searchHotelMatchLocationOrName(String searchTerm) {
        MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery(searchTerm, "name", "location").operator(Operator.AND);

        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQuery)
                .withPageable(PageRequest.of(0, 10)) // Pagination
                .withSort(SortBuilders.scoreSort().order(SortOrder.DESC)) // Sorting by score
                .build();

        return elasticsearchRestTemplate.search(searchQuery, HotelDocument.class)
                .stream().map(SearchHit::getContent)
                .toList();
    }

    public List<HotelDocument> searchByLocation(String location) {
        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("location", location).operator(Operator.AND))
                .build();

        return elasticsearchRestTemplate.search(searchQuery, HotelDocument.class)
                .stream().map(SearchHit::getContent)
                .toList();
    }

    public List<HotelDocument> getByManagerId(Long managerId) {
        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("managerId", managerId))
                .withPageable(PageRequest.of(0, 20))
                .build();

        return elasticsearchRestTemplate.search(searchQuery, HotelDocument.class)
                .stream().map(SearchHit::getContent)
                .toList();
    }

}
