package org.fperspective.academicblogapi.controller;

import java.io.IOException;
import java.util.Collection;

import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private BlogService blogService;

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

    // private Map<String, Blog> blogDB = new HashMap<>(){{
    //     put("1",new Blog("1", "test", "this is a test", "12/9/2023", "1","1", "1" , "1", true));
    // }};

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

    @DeleteMapping("/delete/{id}")
    @CrossOrigin
    public void delete(@PathVariable String blogId) {
        blogService.remove(blogId);
    }

    @PostMapping("/show")
    @CrossOrigin
    public Blog save(@RequestBody Blog blog){
        blog.setStatus(false);
        return blogService.save(blog);
    }
    
}