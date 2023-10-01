package org.fperspective.academicblogapi.model;

public class User {
    private String userID;
    private String userName;
    private String fullName;
    private String email;
    private String campus;
    private Avatar avatar;
    private String subjectID;
    private String role;
    private boolean status;
    public User() {
    }
    public User(String userID, String userName, String fullName, String email, String campus, Avatar avatar,
            String subjectID, String role, boolean status) {
        this.userID = userID;
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.campus = campus;
        this.avatar = avatar;
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
    public Avatar getAvatar() {
        return avatar;
    }
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
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
