package org.fperspective.academicblogapi.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.fperspective.academicblogapi.model.Credential;

public interface CredentialService {

    Optional<Credential> findByEmail(String email);

    void save(Credential credential);

    Credential get(String userId);

    Collection<Credential> get();

    List<Credential> search(String text);

    List<Credential> searchByCampus(String campus);

    public Credential update(Credential user);

    public Credential remove(String userId);

    public Credential saveUser(Credential user);
    
}
