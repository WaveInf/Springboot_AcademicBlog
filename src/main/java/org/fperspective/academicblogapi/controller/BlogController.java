package org.fperspective.academicblogapi.controller;

import java.io.IOException;
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

import ch.qos.logback.core.model.processor.PhaseIndicator;
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

    @GetMapping("/search/{text}")
    @CrossOrigin
    public List<Blog> search(@PathVariable String text) {
        List<Blog> blogs = blogService.search(text);
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

    @GetMapping("/sort/category/{categoryName}")
    @CrossOrigin
    public List<Blog> sortByCategory(@PathVariable String categoryName) {
        List<Blog> blogs = blogService.sortByCategory(categoryName);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/like/{limit}")
    @CrossOrigin
    public List<Blog> sortByMostLiked(@PathVariable String limit) {
        List<Blog> blogs = blogService.sortByMostLiked(limit);
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

    @GetMapping("/sort/year/{year}")
    @CrossOrigin
    public List<Blog> sortYear(@PathVariable String year) {
        List<Blog> blogs = blogService.sortYear(year);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/month/{year}/{month}")
    @CrossOrigin
    public List<Blog> sortMonth(@PathVariable("year") String year, @PathVariable("month") String month) {
        List<Blog> blogs = blogService.sortMonth(year, month);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/week/{year}/{month}/{week}")
    @CrossOrigin
    public List<Blog> sortWeek(@PathVariable("year") String year, @PathVariable("month") String month, @PathVariable("week") String week) {
        List<Blog> blogs = blogService.sortWeek(year, month, week);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
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
        return blogService.save(blog);
    }

    @PostMapping("/update")
    @CrossOrigin
    public Blog update(@RequestBody Blog blog){
        return blogService.update(blog);
    }
    
}