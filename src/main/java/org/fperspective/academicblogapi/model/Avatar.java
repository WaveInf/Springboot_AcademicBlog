package org.fperspective.academicblogapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Avatar {
    private String avatarID;
    private String fileName;

    @JsonIgnore
    private byte[] data;

    private String contentType;

    public Avatar() {
    }

    public Avatar(String avatarID, String fileName, byte[] data, String contentType) {
        this.avatarID = avatarID;
        this.fileName = fileName;
        this.data = data;
        this.contentType = contentType;
    }

    public String getAvatarID() {
        return avatarID;
    }

    public void setAvatarID(String avatarID) {
        this.avatarID = avatarID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    
}
