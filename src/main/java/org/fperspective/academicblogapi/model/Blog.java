package org.fperspective.academicblogapi.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("blogs")
public class Blog {
    @Id
    private String blogId;

    private String blogTitle;

    private String blogContent;

    private String uploadDate;

    private String userID;

    private String categoryID;

    private String subjectID;

    private String tagID;

    private boolean status;
    
    public Blog() {
    }

    public Blog(String blogId, String blogTitle, String blogContent, String uploadDate, String userID,
            String categoryID, String subjectID, String tagID, boolean status) {
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogContent = blogContent;
        this.uploadDate = uploadDate;
        this.userID = userID;
        this.categoryID = categoryID;
        this.subjectID = subjectID;
        this.tagID = tagID;
        this.status = status;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getTagID() {
        return tagID;
    }

    public void setTagID(String tagID) {
        this.tagID = tagID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
