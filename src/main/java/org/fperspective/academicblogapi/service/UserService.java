package org.fperspective.academicblogapi.service;

import java.util.Collection;

import org.fperspective.academicblogapi.model.User;
import org.fperspective.academicblogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService (@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public Collection<User> get() {
        return userRepository.findAll();
    }

    public User get(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void remove(String userId) {
        userRepository.deleteById(userId);
    }

    
}
