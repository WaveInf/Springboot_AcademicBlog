package org.fperspective.academicblogapi.controller;

import java.io.IOException;
import java.util.Collection;

import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Hidden
    @RequestMapping("/")
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

    // private Map<String, Blog> blogDB = new HashMap<>(){{
    //     put("1",new Blog("1", "test", "this is a test", "12/9/2023", "1","1", "1" , "1", true));
    // }};

    @GetMapping("/show")
    public Collection<Blog> get(){
        return blogService.get();
    }

    @GetMapping("/show/{blogId}")
    public Blog get(@PathVariable String blogId) {
        Blog blog = blogService.get(blogId);
        if (blog == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blog;
    }

    @PostMapping("/show")
    public Blog save(@RequestBody Blog blog){
        return blogService.save(blog);
    }
    
}