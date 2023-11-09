package org.fperspective.academicblogapi.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Follow")
@AllArgsConstructor
@NoArgsConstructor
public class Follow {
    
    @Id
    private ObjectId userId;

    private String[] followedUser;

    public String getUserId() {
        return userId.toString();
    }

    public void setUserId(String userId) {
        this.userId = new ObjectId(userId);
    }

    public String[] getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(String[] followedUser) {
        this.followedUser = followedUser;
    }

    
}
