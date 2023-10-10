package org.fperspective.academicblogapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tags")
public class BTag {
    @Id
    private String tagID;
    private String tagName;
    public BTag() {
    }
    public BTag(String tagID, String tagName) {
        this.tagID = tagID;
        this.tagName = tagName;
    }
    public String getTagID() {
        return tagID;
    }
    public void setTagID(String tagID) {
        this.tagID = tagID;
    }
    public String getTagName() {
        return tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    
}
