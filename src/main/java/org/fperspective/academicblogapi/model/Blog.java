package org.fperspective.academicblogapi.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Document("Blog")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Blog {
    @Id
    private String blogId;

    private String blogTitle;

    private String blogContent;

    private String userId;

    private String[] like;

    private Subject[] subject;

    private BTag[] btag;

    @NonNull
    private boolean status;

    @NonNull
    private boolean deleted;

    private Date uploadDate;
    
}
