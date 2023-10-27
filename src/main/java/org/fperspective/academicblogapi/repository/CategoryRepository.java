package org.fperspective.academicblogapi.repository;

import org.fperspective.academicblogapi.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories(basePackages = "org.fperspective.academicblogapi.repository")
public interface CategoryRepository extends MongoRepository<Category, String> {
    
}
