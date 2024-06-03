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
@Document(indexName = "rooms")
public class RoomDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Keyword, name = "hotel_id")
    private Long hotelId;

    @Field(type = FieldType.Integer, name = "number_bed")
    private Integer numberBed;

    @Field(type = FieldType.Long)
    private Long price;

}
