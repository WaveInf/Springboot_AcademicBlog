package org.fperspective.academicblogapi.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @GetMapping("/sort/category/{categoryName}")
    @CrossOrigin
    public List<Blog> sortByCategory(@PathVariable String categoryName) {
        List<Blog> blogs = blogService.sortByCategory(categoryName);
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    @GetMapping("/sort/like")
    @CrossOrigin
    public List<Blog> sortByMostLiked() {
        List<Blog> blogs = blogService.sortByMostLiked();
        if (blogs == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return blogs;
    }

    // @GetMapping("/sort/like")
    // @CrossOrigin
    // public List<String> sortByMostLiked() {
    //     List<String> blogs = blogService.sortByMostLiked();
    //     if (blogs == null)
    //         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    //     return blogs;
    // }

    @GetMapping("/test")
    @CrossOrigin
    public String test() throws JsonMappingException, JsonProcessingException {
         String jsonString = "{\"_id\": {\"$oid\": \"6528d1a718523a8ee09cad0f\"}}";

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Parse the JSON string
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        // Access the value associated with "$oid"
        String oidValue = jsonNode.get("_id").get("$oid").asText();

        // Print the extracted value
        System.out.println(oidValue);
        return oidValue;
    }

    @DeleteMapping("/delete/{blogId}")
    @PreAuthorize("hasRole('ADMIN')")
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