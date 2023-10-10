package org.fperspective.academicblogapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Document(collection = "users")
public class User {

    @Id
    private String userID;

    private String userName;

    private String fullName;

    @Email
    private String email;

    private String campus;

    private String avatarID;

    @NotEmpty
    private String subjectID;
    
    private String role;

    @NonNull
    private boolean status;
    public User() {
    }
    public User(String userID, String userName, String fullName, String email, String campus, String avatarID,
            String subjectID, String role, boolean status) {
        this.userID = userID;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.campus = campus;
        this.avatarID = avatarID;
        this.subjectID = subjectID;
        this.role = role;
        this.status = status;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCampus() {
        return campus;
    }
    public void setCampus(String campus) {
        this.campus = campus;
    }
    public String getAvatarID() {
        return avatarID;
    }
    public void setAvatarID(String avatarID) {
        this.avatarID = avatarID;
    }
    public String getSubjectID() {
        return subjectID;
    }
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
