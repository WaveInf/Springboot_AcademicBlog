package org.fperspective.academicblogapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Avatar {
    private String avatarId;
    private String fileName;

    @JsonIgnore
    private byte[] data;

    private String contentType;

    
}
