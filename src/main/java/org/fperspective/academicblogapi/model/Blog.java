package org.fperspective.academicblogapi.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document("Blog")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Blog {
    @Id
    private String blogId;

    private String blogTitle;

    private String blogContent;

    private String userId;

    private String[] like;

    private String[] commentId;

    private Category category;

    private Subject[] subject;

    private BTag[] btag;

    @NotNull
    private boolean status;

    @NotNull
    private boolean deleted;

    private Date uploadDate;
    
}
