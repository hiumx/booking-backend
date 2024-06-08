package com.hiumx.bookingbackend.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document(indexName = "reviews")
public class ReviewDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Keyword, name = "user_id")
    private Long userId;

    @Field(type = FieldType.Keyword, name = "hotel_id")
    private Long hotelId;

    @Field(type = FieldType.Float)
    private Float point;

}
