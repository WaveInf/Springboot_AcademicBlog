package org.fperspective.academicblogapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.repository.BlogRepository;
import org.fperspective.academicblogapi.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class BlogController {

    // private final BlogService blogService;

    // public BlogController(BlogService blogService){
    //     this.blogService = blogService;
    // }

     @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    private Map<String, Blog> blogDB = new HashMap<>(){{
        put("1",new Blog("1", "test", "this is a test", "12/9/2023", "1","1", "1" , "1", true));
    }};


    // @PostMapping("/blog/save")
    // public void save(){
    //     blogService.saveBlog(blogDB.get("1"));
    // }

    @Autowired
    private BlogRepository blogRepository;

    @PostMapping("/blog/save")
    public Blog saveBlog(@RequestBody Blog blog){
        return blogRepository.save(blog);
    }
    
}