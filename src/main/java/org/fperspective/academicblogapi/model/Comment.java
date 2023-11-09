package org.fperspective.academicblogapi.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("Comment")
public class Comment {
    
    @Id
    private String commentId;

    private String commentContent;

    @NonNull
    private String userId;

    private Date uploadDate;

    @NonNull
    private String blogId;

    private String[] like;

    @NonNull
    private boolean status;
}
