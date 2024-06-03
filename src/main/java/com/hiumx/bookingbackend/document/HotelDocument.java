package com.hiumx.bookingbackend.document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(indexName = "hotels")
public class HotelDocument {
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Object)
    private TypeHotelDocument typeHotel;

    @Field(type = FieldType.Text)
    private String location;

    @Field(type = FieldType.Float)
    private Float fromCenter;

    @Field(type = FieldType.Float)
    private Float rate;

    @Field(type = FieldType.Keyword)
    private Set<ConvenientDocument> convenients;

    @Field(type = FieldType.Nested)
    private Set<RoomDocument> rooms;

    @Field(type = FieldType.Nested)
    private Set<ImageDocument> images;

    @Field(type = FieldType.Nested)
    private Set<ReviewDocument> reviews;

    @Field(type = FieldType.Long)
    private Long managerId;
}
