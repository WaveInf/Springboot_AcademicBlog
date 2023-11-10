package org.fperspective.academicblogapi.service;

import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;
import org.fperspective.academicblogapi.model.Follow;
import org.fperspective.academicblogapi.repository.FollowRepository;
import org.fperspective.academicblogapi.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private SearchRepository searchRepository;

    public Collection<Follow> get() {
        return followRepository.findAll();
    }

    public Follow get(String userId) {
        checkExist(userId);
        return followRepository.findById(userId).orElse(null);
    }

    public List<String> findFollowerCount(String userId){
        checkExist(userId);
        return searchRepository.findFollowerCount(userId);
    }

    public void remove(String userId) {
        followRepository.deleteById(userId);
    }

    public Follow save(Follow follow) {
        return followRepository.save(follow);
    }

    public Follow update(Follow follow) {
        checkExist(follow.getUserId());
        Follow existingFollow = followRepository.findById(follow.getUserId()).get();
        existingFollow.setFollowedUser(follow.getFollowedUser());
        return followRepository.save(existingFollow);
    }

    public void checkExist(String userId){
        Follow check = followRepository.findById(userId).orElse(null);
        if(check == null){
            check = new Follow(new ObjectId(userId), new String[0]);
            followRepository.save(check);
        }
    }
    
}
