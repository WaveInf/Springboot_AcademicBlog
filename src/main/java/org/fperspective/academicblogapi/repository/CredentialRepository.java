package org.fperspective.academicblogapi.repository;

import java.util.Optional;

import org.fperspective.academicblogapi.model.Credential;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends MongoRepository<Credential, String>{
    
    Optional<Credential> findByEmail(String email);
}