package org.fperspective.academicblogapi.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.fperspective.academicblogapi.model.BTag;
import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.model.Subject;
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

    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    public Collection<Blog> get() {
        return blogRepository.findAll();
    }

    public Blog get(String blogId) {
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if(blog.isDeleted()){
            return null;
        }
        else{
            return blog;
        }
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

    public Blog update(Blog blog) {
        Blog existingBlog = blogRepository.findById(blog.getBlogId()).get();
        existingBlog.setBlogTitle(blog.getBlogTitle());
        existingBlog.setBlogContent(blog.getBlogContent());
        existingBlog.setBtag(blog.getBtag());
        existingBlog.setSubject(blog.getSubject());
        return blogRepository.save(existingBlog);
    }

    public void like(Blog blog) {
        Blog existingBlog = blogRepository.findById(blog.getBlogId()).get();
        String[] newlike = blog.getLike();
        Integer count = newlike.length - 1;
        String[] existingLike = existingBlog.getLike();
        Boolean check = true;
        for(String like : existingLike){
            if(like.equals(newlike[count])){
                check = false;
            }

        }
        if(check == true){
            existingBlog.setLike(blog.getLike());
        }
        blogRepository.save(existingBlog);
    }

    public void unlike(Blog blog){
        Blog existingBlog = blogRepository.findById(blog.getBlogId()).get();
        existingBlog.setLike(blog.getLike());
        blogRepository.save(existingBlog);
    }

    public List<Blog> searchByText(String text, String operator) {
        return searchRepository.searchBlogByText(text, operator);
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

    public List<Blog> findAllLikedBlog(String userId){
        List<String> blogs = searchRepository.findAllLikedBlog(userId);
        List<Blog> blogList = new ArrayList<>();
        blogs.forEach((blog) -> blogList.add(blogRepository.findById(blog).get()));
        return blogList;
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
        return searchRepository.sortLatestBlog("-1");
    }

    public List<Blog> sortOldest() {
        return searchRepository.sortLatestBlog("1");
    }

    public List<Blog> sortAll() {
        List<String> blogs = searchRepository.sortBlogAll();
        List<Blog> blogList = new ArrayList<>();
        blogs.forEach((blog) -> blogList.add(blogRepository.findById(blog).get()));
        return blogList;
    }

    public List<Blog> sortDateByTag(String startDate, String endDate, String tag) throws ParseException {
        List<String> blogs = searchRepository.sortBlogByDateAndTag(startDate, endDate, tag);
        List<Blog> blogList = new ArrayList<>();
        blogs.forEach((blog) -> blogList.add(blogRepository.findById(blog).get()));
        return blogList;
    }

    public List<Blog> sortDateBySubject(String startDate, String endDate, String subject) throws ParseException {
        List<String> blogs = searchRepository.sortBlogByDateAndSubject(startDate, endDate, subject);
        List<Blog> blogList = new ArrayList<>();
        blogs.forEach((blog) -> blogList.add(blogRepository.findById(blog).get()));
        return blogList;
    }

    public List<Blog> sortByDateRange(String startDate, String endDate) throws ParseException{
         List<String> blogs = searchRepository.sortBlogByDateRange(startDate, endDate);
        List<Blog> blogList = new ArrayList<>();
        blogs.forEach((blog) -> blogList.add(blogRepository.findById(blog).get()));
        return blogList;
    }

    public void deleteTagInBlog(String tagName) {
        List<String> blogs = searchRepository.findBlogContainTag(tagName);
        if (!blogs.isEmpty()) {
            blogs.forEach((blog) -> {
                Integer index = searchRepository.getIndexTagByBlog(tagName, blog);
                Blog existingBlog = blogRepository.findById(blog).get();
                if (!existingBlog.isDeleted()) {
                    BTag[] existingTag = existingBlog.getBtag();
                    existingTag[index] = searchRepository.findTagByName(tagName);
                    existingBlog.setBtag(existingTag);
                    blogRepository.save(existingBlog);
                }
            });
        }
    }

    public void deleteSubjectInBlog(String subjectName) {
        List<String> blogs = searchRepository.findBlogContainSubject(subjectName);
        if (!blogs.isEmpty()) {
            blogs.forEach((blog) -> {
                Integer index = searchRepository.getIndexSubjectByBlog(subjectName, blog);
                Blog existingBlog = blogRepository.findById(blog).get();
                if (!existingBlog.isDeleted()) {
                    Subject[] existingSubject = existingBlog.getSubject();
                    existingSubject[index] = searchRepository.findSubjectByName(subjectName);
                    existingBlog.setSubject(existingSubject);
                    blogRepository.save(existingBlog);
                }
            });
        }
    }

    public void deleteAllBlogByUser(String userId){
        List<Blog> blogs = searchRepository.searchAllBlogByUser(userId);
        if(!blogs.isEmpty()){
            blogs.forEach((blog) -> {
                    blog.setDeleted(true);
                    blog.setStatus(false);
                    blogRepository.save(blog);
            });
        }
    }

    public Integer getIndex(String tagName, String blogId) {
        return searchRepository.getIndexTagByBlog(tagName, blogId);
    }

    public List<Blog> findUnapprovedBlogs(String operator) {
        List<Blog> blogs = searchRepository.findUnapprovedBlogs(operator);
        return blogs;
    }
}
