package org.fperspective.academicblogapi.repository;

import org.fperspective.academicblogapi.model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface BlogRepository extends MongoRepository<Blog, String> {
    
}


