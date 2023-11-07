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

    public List<Blog> searchByText(String text, String operator) {
        return searchRepository.searchBlogByText(text,operator);
    }

    public List<Blog> searchByCategory(String categoryName, String time) {
        return searchRepository.searchBlogByCategory(categoryName, time);
    }

     public List<Blog> searchBySubject(String subjectName, String time) {
        return searchRepository.searchBlogBySubject(subjectName, time);
    }

     public List<Blog> searchByTag(String tagName, String time) {
        return searchRepository.searchBlogByTag(tagName, time);
    }

    public List<Blog> searchByUser(String userId, String time) {
        return searchRepository.searchBlogByUser(userId, time);
    }

    public List<Blog> findMostLikedBlogByText(String text) {
        List<String> blogs = searchRepository.findMostLikedBlogByText(text);
        List<Blog> blogList = new ArrayList<>();
        blogs.forEach((blog) -> blogList.add(blogRepository.findById(blog).get()));
        return blogList;
    }

    public List<Blog> findMostLikedBlogByCategory(String category) {
        List<String> blogs = searchRepository.findMostLikedBlogByCategory(category);
        List<Blog> blogList = new ArrayList<>();
        blogs.forEach((blog) -> blogList.add(blogRepository.findById(blog).get()));
        return blogList;
    }

    public List<Blog> findMostLikedBlogBySubject(String subject) {
        List<String> blogs = searchRepository.findMostLikedBlogBySubject(subject);
        List<Blog> blogList = new ArrayList<>();
        blogs.forEach((blog) -> blogList.add(blogRepository.findById(blog).get()));
        return blogList;
    }

    public List<Blog> findMostLikedBlogByTag(String tag) {
        List<String> blogs = searchRepository.findMostLikedBlogByTag(tag);
        List<Blog> blogList = new ArrayList<>();
        blogs.forEach((blog) -> blogList.add(blogRepository.findById(blog).get()));
        return blogList;
    }

    public List<Blog> findMostLikedBlogByUser(String userId) {
        List<String> blogs = searchRepository.findMostLikedBlogByUser(userId);
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
