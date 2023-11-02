package org.fperspective.academicblogapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    public List<BTag> findMostUsedTag(String limit) {
        List<String> tags = searchRepository.findMostUsedTag(limit);
        List<BTag> tagList = new ArrayList<>();
        tags.forEach((tag) -> tagList.add(tagRepository.findById(tag).orElse(null)));
        return tagList;
    }

    public List<String> test(String limit) {
        List<String> tags = searchRepository.findMostUsedTag(limit);
        // List<BTag> tagList = new ArrayList<>();
        // tags.forEach((tag) -> tagList.add(tagRepository.findById(tag).get()));
        return tags;
    }

    public Integer findMostUsedTagCount(String tagName) {
        return searchRepository.findMostUsedTagCount(tagName);
    }
    
    public BTag update(BTag tag){
        BTag existingTag = tagRepository.findById(tag.getTagId()).get();
        existingTag.setTagName(tag.getTagName());
        return tagRepository.save(existingTag);
    }
}
