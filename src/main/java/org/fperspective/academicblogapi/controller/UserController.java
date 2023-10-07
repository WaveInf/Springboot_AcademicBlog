package org.fperspective.academicblogapi.controller;

import java.io.IOException;
import java.util.Collection;

import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.model.User;
import org.fperspective.academicblogapi.service.UserService;
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
@Tag(name = "User", description = "User Management API")
@RequestMapping("/api/v1/user")
@CrossOrigin
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(implementation = Blog.class), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class UserController {

    @Autowired
    private UserService userService;

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

     @GetMapping("/show")
     @CrossOrigin
    public Collection<User> get(){
        return userService.get();
    }

    @GetMapping("/show/{userId}")
    @CrossOrigin
    
    public User get(@PathVariable String userId) {
        User user = userService.get(userId);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return user;
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin
    public void delete(@PathVariable String userId) {
        userService.remove(userId);
    }

    @PostMapping("/show")
    @CrossOrigin
    public User save(@RequestBody User user){
        return userService.save(user);
    }
}
