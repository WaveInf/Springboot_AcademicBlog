package org.fperspective.academicblogapi.repository;

import java.util.Optional;

import org.fperspective.academicblogapi.model.Credential;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface CredentialRepository extends MongoRepository<Credential, String>{
    
    Optional<Credential> findByEmail(String email);
}