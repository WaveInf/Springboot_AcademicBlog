package org.fperspective.academicblogapi.repository;

import org.fperspective.academicblogapi.model.Bookmark;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends MongoRepository<Bookmark, String> {
    
}
