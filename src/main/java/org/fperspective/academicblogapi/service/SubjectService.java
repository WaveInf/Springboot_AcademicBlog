package org.fperspective.academicblogapi.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.fperspective.academicblogapi.model.Subject;
import org.fperspective.academicblogapi.repository.SearchRepository;
import org.fperspective.academicblogapi.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    
    @Autowired
    // @Lazy
    private SubjectRepository subjectRepository;

    @Autowired
    private SearchRepository searchRepository;

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

    public Subject enable(String subjectId) {
        Subject existingSubject = subjectRepository.findById(subjectId).get();
        existingSubject.setStatus(true);
        return subjectRepository.save(existingSubject);
    }

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> search(String text){
        String textUpperCase = text.toUpperCase();
        List<Subject> subjects = searchRepository.findSubjectListByName(textUpperCase);
        return subjects;
    }

    public List<Subject> findMostUsedSubject(String limit) {
        List<String> subjects = searchRepository.findMostUsedSubject(limit);
        List<Subject> subjectList = new ArrayList<>();
        subjects.forEach((subject) ->{
            Subject newSubject = subjectRepository.findById(subject).orElse(null);
            // if(newSubject.isStatus()){
                subjectList.add(newSubject);
            // }
        });
        return subjectList;
    }

    public List<Subject> findMostUsedSubjectByDate(String limit, String startDate, String endDate) throws ParseException {
        List<String> subjects = searchRepository.findMostUsedSubjectByDate(limit, startDate, endDate);
        List<Subject> subjectList = new ArrayList<>();
        subjects.forEach((subject) ->{
            Subject newSubject = subjectRepository.findById(subject).orElse(null);
            // if(newSubject.isStatus()){
                subjectList.add(newSubject);
            // }
        });
        return subjectList;
    }

    public List<Subject> findTagByBlog(String blogId) {
        List<String> subjects = searchRepository.findSubjectByBlog(blogId);
        List<Subject> subjectList = new ArrayList<>();
        subjects.forEach((subject) -> {
            Subject newSubject = subjectRepository.findById(subject).orElse(null);
            if(newSubject.isStatus()){
                subjectList.add(newSubject);
            }
        });
        return subjectList;
    }

    public Integer findMostUsedTagCount(String subjectName) {
        return searchRepository.findMostUsedSubjectCount(subjectName);
    }

    public Subject update(Subject subject) {
        Subject existingSubject = subjectRepository.findById(subject.getSubjectId()).get();
        existingSubject.setSubjectName(subject.getSubjectName());
        return subjectRepository.save(existingSubject);
    }
    
}
