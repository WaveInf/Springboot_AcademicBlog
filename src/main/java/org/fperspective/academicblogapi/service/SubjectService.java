package org.fperspective.academicblogapi.service;

import java.util.Collection;

import org.fperspective.academicblogapi.model.Subject;
import org.fperspective.academicblogapi.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    
    @Autowired
    // @Lazy
    private SubjectRepository subjectRepository;

    public Collection<Subject> get() {
        return subjectRepository.findAll();
    }

    public Subject get(String subjectId) {
        return subjectRepository.findById(subjectId).orElse(null);
    }

    public Subject remove(String subjectId) {
        Subject existingSubject = subjectRepository.findById(subjectId).get();
        existingSubject.setStatus(false);
        return subjectRepository.save(existingSubject);
    }

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject update(Subject subject) {
        Subject existingSubject = subjectRepository.findById(subject.getSubjectId()).get();
        existingSubject.setSubjectName(subject.getSubjectName());
        return subjectRepository.save(existingSubject);
    }
}
