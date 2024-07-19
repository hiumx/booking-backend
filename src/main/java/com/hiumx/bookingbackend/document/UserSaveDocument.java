package com.hiumx.bookingbackend.document;


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
@Document(indexName = "user_saved")
public class UserSaveDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Keyword, name = "user_id")
    private Long userId;

    @Field(type = FieldType.Keyword, name = "hotels_saved")
    private Set<Long> hotelsId;


}
