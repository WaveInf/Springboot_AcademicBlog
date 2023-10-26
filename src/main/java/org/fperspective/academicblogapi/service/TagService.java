package org.fperspective.academicblogapi.service;

import java.util.Collection;
import java.util.Map;

import org.fperspective.academicblogapi.model.BTag;
import org.fperspective.academicblogapi.repository.SearchRepository;
import org.fperspective.academicblogapi.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    
    @Autowired
    // @Lazy
    private TagRepository tagRepository;

    @Autowired
    // @Lazy
    private SearchRepository searchRepository;

    public Collection<BTag> get() {
        return tagRepository.findAll();
    }

    public BTag get(String tagId) {
        return tagRepository.findById(tagId).orElse(null);
    }

    public BTag remove(String tagId) {
        BTag existingTag = tagRepository.findById(tagId).get();
        existingTag.setStatus(false);
        return tagRepository.save(existingTag);
    }

    public BTag save(BTag tag) {
        return tagRepository.save(tag);
    }

    public Map<BTag, Integer> findMostUsedTag() {
        return searchRepository.findMostUsedTag();
    }
    
    public BTag update(BTag tag){
        BTag existingTag = tagRepository.findById(tag.getTagId()).get();
        existingTag.setTagName(tag.getTagName());
        return tagRepository.save(existingTag);
    }
}
