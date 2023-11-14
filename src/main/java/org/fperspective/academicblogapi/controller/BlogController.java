package org.fperspective.academicblogapi.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@Tag(name = "Blog", description = "Blog Management API")
@RequestMapping("/api/v1/blog")
@CrossOrigin
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(implementation = Blog.class), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class BlogController {

    @Autowired
    // @Lazy
    private BlogService blogService;

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/show")
    @CrossOrigin
    public Collection<Blog> get(){
        return blogService.get();
    }

    @GetMapping("/show/{blogId}")
    @CrossOrigin
    public Blog get(@PathVariable String blogId) {
        Blog blog = blogService.get(blogId);
        if (blog == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blog;
    }

    @GetMapping("/search/text/{text}/{time}")
    @CrossOrigin
    //time: 1 or -1, 1 for oldest, -1 for newest
    //search by blogTitle
    public List<Blog> searchByText(@PathVariable("text") String text, @PathVariable("time") String time) {
        List<Blog> blogs = blogService.searchByText(text, time);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/search/category/{categoryName}/{time}")
    @CrossOrigin
    //time: 1 or -1, 1 for oldest, -1 for newest
    //search by categoryName
    public List<Blog> searchByCategory(@PathVariable("categoryName") String categoryName, @PathVariable("time") String time) {
        List<Blog> blogs = blogService.searchByCategory(categoryName, time);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/search/subject/{subjectName}/{time}")
    @CrossOrigin
    //time: 1 or -1, 1 for oldest, -1 for newest
    //search by subjectName
    public List<Blog> searchBySubject(@PathVariable("subjectName") String subjectName, @PathVariable("time") String time) {
        List<Blog> blogs = blogService.searchBySubject(subjectName, time);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/search/tag/{tagName}/{time}")
    @CrossOrigin
    //time: 1 or -1, 1 for oldest, -1 for newest
    //search by tagName
    public List<Blog> searchByTag(@PathVariable("tagName") String tagName, @PathVariable("time") String time) {
        List<Blog> blogs = blogService.searchByTag(tagName, time);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/search/user/{userId}/{time}")
    @CrossOrigin
    //time: 1 or -1, 1 for oldest, -1 for newest
    //search by userId
    public List<Blog> searchByUser(@PathVariable("userId") String userId, @PathVariable("time") String time) {
        List<Blog> blogs = blogService.searchByUser(userId, time);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/text/{text}")
    @CrossOrigin
    //sort most popular blog by blogTitle
    public List<Blog> sortByMostLikedByText(@PathVariable("text") String text) {
        List<Blog> blogs = blogService.findMostLikedBlogByText(text);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/category/{categoryName}")
    @CrossOrigin
    //sort most popular blog by categoryName
    public List<Blog> sortByMostLikedByCategory(@PathVariable("categoryName") String categoryName) {
        List<Blog> blogs = blogService.findMostLikedBlogByCategory(categoryName);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/subject/{subjectName}")
    @CrossOrigin
    //sort most popular blog by subjectName
    public List<Blog> sortByMostLikedBySubject(@PathVariable("subjectName") String subjectName) {
        List<Blog> blogs = blogService.findMostLikedBlogBySubject(subjectName);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/tag/{tagName}")
    @CrossOrigin
    //sort most popular blog by tagName
    public List<Blog> sortByMostLikedByTag(@PathVariable("tagName") String tagName) {
        List<Blog> blogs = blogService.findMostLikedBlogByTag(tagName);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/user/{userId}")
    @CrossOrigin
    //sort most popular blog by user
    public List<Blog> sortByMostLikedByUser(@PathVariable("userId") String userId) {
        List<Blog> blogs = blogService.findMostLikedBlogByUser(userId);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/latest")
    @CrossOrigin
    public List<Blog> sortLatest() {
        List<Blog> blogs = blogService.sortLatest();
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/oldest")
    @CrossOrigin
    public List<Blog> sortOldest() {
        List<Blog> blogs = blogService.sortOldest();
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/all")
    @CrossOrigin
    //sort all blog by like
    public List<Blog> sortAll() {
        List<Blog> blogs = blogService.sortAll();
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/blog/year/{year}")
    @CrossOrigin
    public List<Blog> sortYear(@PathVariable String year) {
        List<Blog> blogs = blogService.sortYear(year);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/blog/month/{year}/{month}")
    @CrossOrigin
    public List<Blog> sortMonth(@PathVariable("year") String year, @PathVariable("month") String month) {
        List<Blog> blogs = blogService.sortMonth(year, month);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/blog/week/{year}/{month}/{week}")
    @CrossOrigin
    public List<Blog> sortWeek(@PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("week") String week) {
        List<Blog> blogs = blogService.sortWeek(year, month, week);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/tag/year/{year}/{tagName}")
    @CrossOrigin
    public List<Blog> sortYearByTag(@PathVariable("year") String year, @PathVariable("tagName") String tagName) {
        List<Blog> blogs = blogService.sortYearByTag(year, tagName);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/tag/month/{year}/{month}/{tagName}")
    @CrossOrigin
    public List<Blog> sortMonth(@PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("tagName") String tagName) {
        List<Blog> blogs = blogService.sortMonthByTag(year, month, tagName);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/tag/week/{year}/{month}/{week}/{tagName}")
    @CrossOrigin
    public List<Blog> sortWeek(@PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("week") String week, @PathVariable("tagName") String tagName) {
        List<Blog> blogs = blogService.sortWeekByTag(year, month, week, tagName);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/subject/year/{year}/{subjectName}")
    @CrossOrigin
    public List<Blog> sortYearBySubject(@PathVariable("year") String year, @PathVariable("subjectName") String subjectName) {
        List<Blog> blogs = blogService.sortYearBySubject(year, subjectName);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/subject/month/{year}/{month}/{subjectName}")
    @CrossOrigin
    public List<Blog> sortMonthBySubject(@PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("subjectName") String subjectName) {
        List<Blog> blogs = blogService.sortMonthBySubject(year, month, subjectName);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/subject/week/{year}/{month}/{week}/{subjectName}")
    @CrossOrigin
    public List<Blog> sortWeekBySubject(@PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("week") String week, @PathVariable("subjectName") String subjectName) {
        List<Blog> blogs = blogService.sortWeekBySubject(year, month, week, subjectName);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/date/range/{startDate}/{endDate}")
    @CrossOrigin
    public List<Blog> sortBlogByDateRange(@PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) throws ParseException {
        List<Blog> blogs = blogService.sortByDateRange(startDate, endDate);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/approve/{operator}")
    @CrossOrigin
    public List<Blog> findUnapprovedBlogs(@PathVariable String operator) {
        List<Blog> blogs = blogService.findUnapprovedBlogs(operator);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/index/{tagName}/{blogId}")
    @CrossOrigin
    public Integer findUnapprovedBlogs(@PathVariable("tagName") String tagName, @PathVariable("blogId") String blogId) {
        Integer blogs = blogService.getIndex(tagName, blogId);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @DeleteMapping("/user/{userId}")
    // @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    public void deleteAllBlogByUser(@PathVariable String userId) {
        blogService.deleteAllBlogByUser(userId);
    }

    @DeleteMapping("/tag/{tagName}")
    // @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    public void deleteBlogByTag(@PathVariable String tagName) {
        blogService.deleteTagInBlog(tagName);
    }

    @DeleteMapping("/subject/{subjectName}")
    // @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    public void deleteBlogBySubject(@PathVariable String subjectName) {
        blogService.deleteSubjectInBlog(subjectName);
    }

    @DeleteMapping("/delete/{blogId}")
    // @PreAuthorize("hasRole('ADMIN')")
    @CrossOrigin
    public void delete(@PathVariable String blogId) {
        blogService.remove(blogId);
    }

    @DeleteMapping("/approve/{blogId}")
    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @CrossOrigin
    public void approve(@PathVariable String blogId) {
        blogService.approve(blogId);
    }

    @PostMapping("/show")
    @CrossOrigin
    public Blog save(@RequestBody Blog blog){
        blog.setStatus(false);
        blog.setDeleted(false);
        blog.setLike(new String[0]);
        if(blog.getSubject() == null){
            blog.setSubject(null);
        }
        return blogService.save(blog);
    }

    @PostMapping("/update")
    @CrossOrigin
    public Blog update(@RequestBody Blog blog){
        return blogService.update(blog);
    }

    @PostMapping("/like")
    @CrossOrigin
    public void like(@RequestBody Blog blog){
        blogService.like(blog);
    }

    @PostMapping("/unlike")
    @CrossOrigin
    public void unlike(@RequestBody Blog blog){
        blogService.unlike(blog);
    }
    
}