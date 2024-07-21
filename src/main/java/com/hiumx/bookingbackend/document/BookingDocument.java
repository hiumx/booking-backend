package com.hiumx.bookingbackend.document;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "bookings")
public class BookingDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Keyword, name = "user_id")
    private Long userId;

    @Field(type = FieldType.Keyword, name = "hotel_id")
    private Long hotelId;

    @Field(type = FieldType.Keyword, name = "room_id")
    private Set<Long> roomsId;

    @Field(type = FieldType.Integer, name = "number_adult")
    private Integer numberAdult;

    @Field(type = FieldType.Integer, name = "number_children")
    private Integer numberChildren;

    @Field(type = FieldType.Date, name = "start_date")
    private LocalDate startDate;

    @Field(type = FieldType.Date, name = "end_date")
    private LocalDate endDate;

    @Field(type = FieldType.Keyword, name = "payment_card_id")
    private Long paymentCardId;
}
