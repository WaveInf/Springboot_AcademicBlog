package org.fperspective.academicblogapi.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Document("Bookmark")
@AllArgsConstructor
@NoArgsConstructor
public class Bookmark {
    
    @Id
    private ObjectId userId;

    private String[] bookmarkedPost;

    public String getUserId() {
        return userId.toString();
    }

    public void setUserId(String userId) {
        this.userId = new ObjectId(userId);
    }

    public String[] getBookmarkedPost() {
        return bookmarkedPost;
    }

    public void setBookmarkedPost(String[] bookmarkedPost) {
        this.bookmarkedPost = bookmarkedPost;
    }

    
}
