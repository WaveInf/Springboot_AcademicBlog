package org.fperspective.academicblogapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.fperspective.academicblogapi.model.BTag;
import org.fperspective.academicblogapi.repository.SearchRepository;
import org.fperspective.academicblogapi.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public BTag enable(String tagId) {
        BTag existingTag = tagRepository.findById(tagId).get();
        existingTag.setStatus(true);
        return tagRepository.save(existingTag);
    }

    public BTag save(BTag tag) {
        return tagRepository.save(tag);
    }

    public List<BTag> search(String text){
        List<BTag> tags = searchRepository.findTagListByName(text);
        return tags;
    }

    public BTag searchOne(String text){
        BTag tags = searchRepository.findTagByName(text);
        return tags;
    }

    public List<BTag> findMostUsedTag(String limit) {
        List<String> tags = searchRepository.findMostUsedTag(limit);
        List<BTag> tagList = new ArrayList<>();
        tags.forEach((tag) ->{
            BTag btag = tagRepository.findById(tag).orElse(null);
            if(btag.isStatus()){
                tagList.add(btag);
            }
        });
        return tagList;
    }

    public List<BTag> findTagByBlog(String blogId) {
        List<String> tags = searchRepository.findTagByBlog(blogId);
        List<BTag> tagList = new ArrayList<>();
        tags.forEach((tag) -> {
            BTag btag = tagRepository.findById(tag).orElse(null);
            if(btag.isStatus()){
                tagList.add(btag);
            }
        });
        return tagList;
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
