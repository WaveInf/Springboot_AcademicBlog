package org.fperspective.academicblogapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.fperspective.academicblogapi.model.Credential;
import org.fperspective.academicblogapi.repository.CredentialRepository;
import org.fperspective.academicblogapi.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    // @Lazy
    private CredentialRepository credentialRepository;

    @Autowired
    // @Lazy
    private SearchRepository searchRepository;

    @Override
    public Optional<Credential> findByEmail(String email) {
        return credentialRepository.findByEmail(email);
    }

    @Override
    public void save(Credential credential) {
        credentialRepository.save(credential);
    }

    @Override
    public Collection<Credential> get() {
        return credentialRepository.findAll();
    }

    @Override
    public Credential get(String userId) {
        return credentialRepository.findById(userId).orElse(null);
    }
    
    @Override
    public Credential remove(String userId) {
        Credential existingUser = credentialRepository.findById(userId).get();
        existingUser.setStatus(false);
        return credentialRepository.save(existingUser);
    }

    @Override
    public Credential saveUser(Credential user){
        return credentialRepository.save(user);
    }

    @Override
    public List<Credential> search(String text) {
        return searchRepository.searchUserByText(text);
    }

    @Override
    public Credential update(Credential user){
        Credential existingUser = credentialRepository.findById(user.getUserID()).get();
        existingUser.setAvatarUrl(user.getAvatarUrl());
        existingUser.setBio(user.getBio());
        existingUser.setCategory(user.getCategory());
        existingUser.setUsername(user.getUsername());
        return credentialRepository.save(existingUser);
    }

    @Override
    public List<Credential> searchByCampus(String campus) {
        return searchRepository.searchUserByCampus(campus);
    }

    @Override
    public List<Credential> findRecommendedUser(String[] tagName) {
        List<String> users = searchRepository.findRecommendedUser(tagName);
        List<Credential> userList = new ArrayList<>();
        users.forEach((user) -> userList.add(credentialRepository.findById(user).get()));
        return userList;
    }
}
