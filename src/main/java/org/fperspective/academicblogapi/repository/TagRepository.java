package org.fperspective.academicblogapi.repository;

import org.fperspective.academicblogapi.model.BTag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends MongoRepository<BTag, String> {
    
}
