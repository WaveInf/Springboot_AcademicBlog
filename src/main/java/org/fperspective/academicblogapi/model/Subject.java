package org.fperspective.academicblogapi.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import nonapi.io.github.classgraph.json.Id;

@Document("subjects")
public class Subject {
    @Id
    private String subjectID;

    @NonNull
    private String subjectName;
    public Subject() {
    }
    public Subject(String subjectID, String subjectName) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }
    public String getSubjectID() {
        return subjectID;
    }
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    
}
