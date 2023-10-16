package org.fperspective.academicblogapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Subject")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subject {

    @Id
    private String subjectId;

    @NonNull
    private String subjectName;

    private boolean status;
    
}
