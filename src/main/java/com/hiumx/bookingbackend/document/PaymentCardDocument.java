package com.hiumx.bookingbackend.document;

import com.hiumx.bookingbackend.entity.User;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "payment_cards")
public class PaymentCardDocument {
    @Id
    private Long id;

    @Field(type = FieldType.Text, name = "cart_number")
    private String cardNumber;

    @Field(type = FieldType.Object)
    private User user;

    @Field(type = FieldType.Nested)
    private Set<Long> bookingsId;
}
