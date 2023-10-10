package org.fperspective.academicblogapi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Home", description = "Home Management API")
// @RequestMapping("/api/v1")
@CrossOrigin
public class HomeController {
    @GetMapping("/")
    public String home(@RequestParam(name = "logout", required = false, defaultValue = "true") boolean logout) {
        return "index";
    }

    @GetMapping("/login")
    String login() {
        return "app-user/login";
    }
}
