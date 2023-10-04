package org.fperspective.academicblogapi.controller;

import java.io.IOException;
import java.util.Collection;

import org.fperspective.academicblogapi.model.User;
import org.fperspective.academicblogapi.service.UserService;
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
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Hidden
    @RequestMapping("/")
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

     @GetMapping("/show")
    public Collection<User> get(){
        return userService.get();
    }

    @GetMapping("/show/{userId}")
    public User get(@PathVariable String userId) {
        User user = userService.get(userId);
        if (user == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return user;
    }

    @PostMapping("/show")
    public User save(@RequestBody User user){
        return userService.save(user);
    }
}
