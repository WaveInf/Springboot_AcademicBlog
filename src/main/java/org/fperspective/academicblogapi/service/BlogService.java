package org.fperspective.academicblogapi.service;

import java.util.Collection;
import java.util.List;

import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.repository.BlogRepository;
import org.fperspective.academicblogapi.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private SearchRepository searchRepository;

    public Blog save(Blog blog){
        return blogRepository.save(blog);
    }

    public Collection<Blog> get() {
        return blogRepository.findAll();
    }

    public Blog get(String blogId) {
        return blogRepository.findById(blogId).orElse(null);
    }

    public void remove(String blogId) {
        blogRepository.deleteById(blogId);
    }

    public List<Blog> search(String text) {
        return searchRepository.searchBlogByText(text);
    }

}
