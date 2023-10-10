package org.fperspective.academicblogapi.repository;

import org.fperspective.academicblogapi.model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends MongoRepository<Subject, String>{
    
}
