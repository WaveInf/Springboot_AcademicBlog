package org.fperspective.academicblogapi.service;

import java.util.Collection;
import java.util.List;

import org.fperspective.academicblogapi.model.User;
import org.fperspective.academicblogapi.repository.SearchRepository;
import org.fperspective.academicblogapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SearchRepository searchRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public Collection<User> get() {
        return userRepository.findAll();
    }

    public User get(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User remove(String userId) {
        User existingUser = userRepository.findById(userId).get();
        existingUser.setStatus(false);
        return userRepository.save(existingUser);
    }

    // public List<User> search(String text) {
    //     return searchRepository.searchUserByText(text);
    // }

    public User update(User user){
        User existingUser = userRepository.findById(user.getUserId()).get();
        existingUser.setAvatarId(user.getAvatarId());
        existingUser.setCategory(user.getCategory());
        existingUser.setUserName(user.getUserName());
        return userRepository.save(existingUser);
    }

    // public List<User> searchByCampus(String campus) {
    //     return searchRepository.searchUserByCampus(campus);
    // }
    
}
