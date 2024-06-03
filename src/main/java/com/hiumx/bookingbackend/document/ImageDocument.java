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
@Document(indexName = "images")
public class ImageDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String url;

    @Field(type = FieldType.Long)
    private Long hotelId;
}
