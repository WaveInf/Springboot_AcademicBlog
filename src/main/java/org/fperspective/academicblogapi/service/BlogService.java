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
    @Lazy
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
        existingBlog.setDeleted(true);
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

    public List<Blog> searchByCategory(String categoryName) {
        return searchRepository.searchBlogByCategory(categoryName);
    }

    public List<Blog> searchByUser(String userId) {
        return searchRepository.searchBlogByUser(userId);
    }

    public List<Blog> sortByMostLiked(String limit) {
        List<String> blogs = searchRepository.findMostLikedBlog(limit);
        List<Blog> blogList = new ArrayList<>();
        blogs.forEach((blog) -> blogList.add(blogRepository.findById(blog).get()));
        return blogList;
    }

    public List<Blog> sortLatest() {
        return searchRepository.sortLatestBlog();
    }

    public List<Blog> sortYear(String year) {
        List<String> blogs = searchRepository.sortBlogByYear(year);
        List<Blog> blogList = new ArrayList<>();
        blogs.forEach((blog) -> blogList.add(blogRepository.findById(blog).get()));
        return blogList;
    }

    public List<Blog> sortMonth(String year, String month) {
        List<String> blogs = searchRepository.sortBlogByMonth(year, month);
        List<Blog> blogList = new ArrayList<>();
        blogs.forEach((blog) -> blogList.add(blogRepository.findById(blog).get()));
        return blogList;
    }

    public List<Blog> sortWeek(String year, String month, String week) {
       List<String> blogs = searchRepository.sortBlogByWeek(year, month, week);
        List<Blog> blogList = new ArrayList<>();
        blogs.forEach((blog) -> blogList.add(blogRepository.findById(blog).get()));
        return blogList;
    }


    public List<Blog> findUnapprovedBlogs(String operator){
        List<Blog> blogs = searchRepository.findUnapprovedBlogs(operator);
        return blogs;
    }
}
