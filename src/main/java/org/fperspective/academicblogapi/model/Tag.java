package org.fperspective.academicblogapi.model;

public class Tag {
    private String tagID;
    private String tagName;
    public Tag() {
    }
    public Tag(String tagID, String tagName) {
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
