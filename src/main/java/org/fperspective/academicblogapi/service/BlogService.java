package org.fperspective.academicblogapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.repository.BlogRepository;
import org.fperspective.academicblogapi.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    // @Lazy
    private BlogRepository blogRepository;

    @Autowired
    // @Lazy
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

    public Blog remove(String blogId) {
        Blog existingBlog = blogRepository.findById(blogId).get();
        existingBlog.setStatus(false);
        return blogRepository.save(existingBlog);
    }

    public Blog approve(String blogId) {
        Blog existingBlog = blogRepository.findById(blogId).get();
        existingBlog.setStatus(true);
        return blogRepository.save(existingBlog);
    }

    public Blog update(Blog blog){
        Blog existingBlog =  blogRepository.findById(blog.getBlogId()).get();
        existingBlog.setBlogTitle(blog.getBlogTitle());
        existingBlog.setBlogContent(blog.getBlogContent());
        existingBlog.setBtag(blog.getBtag());
        existingBlog.setSubject(blog.getSubject());
        existingBlog.setCategory(blog.getCategory());
        return blogRepository.save(existingBlog);
    }

    public List<Blog> search(String text) {
        return searchRepository.searchBlogByText(text);
    }

    public List<Blog> sortByCategory(String categoryName) {
        return searchRepository.searchBlogByCategory(categoryName);
    }

    public List<Blog> sortByMostLiked() {
        List<String> blogs = searchRepository.findMostLikedBlog();
        List<Blog> blogList = new ArrayList<>();
        blogs.forEach((blog) -> blogList.add(blogRepository.findById(blog).get()));
        return blogList;
    }

    // public List<String> sortByMostLiked(){
    //     return searchRepository.findMostLikedBlog();
    // }
}
