package org.fperspective.academicblogapi.service;

import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService (@Autowired BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog saveBlog(@RequestBody Blog blog){
        return blogRepository.save(blog);
    }
}
