package org.fperspective.academicblogapi.service;

import java.util.Collection;

import org.fperspective.academicblogapi.model.Subject;
import org.fperspective.academicblogapi.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    
    @Autowired
    private SubjectRepository subjectRepository;

    public Collection<Subject> get() {
        return subjectRepository.findAll();
    }

    public Subject get(String subjectId) {
        return subjectRepository.findById(subjectId).orElse(null);
    }

    public void remove(String subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    
}
