package org.fperspective.academicblogapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("User")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    private String userId;

    private String userName;

    private String fullName;

    @Email
    private String email;

    private String campus;

    private String avatarId;

    @NotEmpty
    private String categoryId;
    
    private String role;

    @NonNull
    private boolean status;
    
}
