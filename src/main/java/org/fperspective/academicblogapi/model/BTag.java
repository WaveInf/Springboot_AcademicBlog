package org.fperspective.academicblogapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("BTag")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BTag {
    
    @Id
    private String tagId;

    private String tagName;

    private boolean status;

}
