package org.fperspective.academicblogapi.model;

import org.springframework.data.annotation.Id;

public class Category {
    @Id
    private String categoryID;
    private String categoryName;
    private boolean status;
    
    public Category() {
    }
    public Category(String categoryID, String categoryName, boolean status) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.status = status;
    }
    public String getCategoryID() {
        return categoryID;
    }
    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    

}
