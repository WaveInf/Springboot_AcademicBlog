package org.fperspective.academicblogapi.service;

import java.util.Collection;

import org.fperspective.academicblogapi.model.Follow;
import org.fperspective.academicblogapi.repository.FollowRepository;
import org.fperspective.academicblogapi.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Integral;
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
        return followRepository.findById(userId).orElse(null);
    }

    public Integer findFollowerCount(String userId){
        return searchRepository.findFollowerCount(userId);
    }

    public void remove(String userId) {
        followRepository.deleteById(userId);
    }

    public Follow save(Follow follow) {
        return followRepository.save(follow);
    }

    public Follow update(Follow follow) {
        Follow existingFollow = followRepository.findById(follow.getUserId()).get();
        existingFollow.setFollowedUser(follow.getFollowedUser());
        return followRepository.save(existingFollow);
    }
    
}
