package org.fperspective.academicblogapi.repository;

import org.fperspective.academicblogapi.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    
}
