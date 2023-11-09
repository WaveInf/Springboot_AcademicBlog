package org.fperspective.academicblogapi.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.fperspective.academicblogapi.model.Credential;
import org.springframework.stereotype.Service;

@Service
public interface CredentialService {

    Optional<Credential> findByEmail(String email);

    List<Credential> findRecommendedUser(String search, String currentUser);

    List<String> test(String search);

    void save(Credential credential);

    Credential get(String userId);

    Collection<Credential> get();

    List<Credential> search(String text);

    List<Credential> searchByCampus(String campus);

    Credential searchByEmail(String email);

    Credential update(Credential user);

    Credential remove(String userId);

    Credential saveUser(Credential user);
    
}
