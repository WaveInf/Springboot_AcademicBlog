package org.fperspective.academicblogapi.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.fperspective.academicblogapi.model.Follow;
import org.fperspective.academicblogapi.service.FollowService;
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
@Tag(name = "Follow", description = "Follow Management API")
@RequestMapping("/api/v1/follow")
@CrossOrigin
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(implementation = Follow.class), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class FollowController {
    @Autowired
    // @Lazy
    private FollowService followService;

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/show")
    @CrossOrigin
    public Collection<Follow> get(){
        return followService.get();
    }

    @GetMapping("/show/user/{userId}")
    @CrossOrigin
    public Follow get(@PathVariable String userId) {
        Follow follow = followService.get(userId);
        if (follow == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return follow;
    }

    @GetMapping("/show/count/{userId}")
    @CrossOrigin
    public List<String> findFollowerCount(@PathVariable String userId) {
        List<String> follow = followService.findFollowerCount(userId);
        if (follow == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return follow;
    }


    @DeleteMapping("/delete/{userId}")
    @CrossOrigin
    public void delete(@PathVariable String userId) {
        followService.remove(userId);
    }

    @PostMapping("/show")
    @CrossOrigin
    public Follow save(@RequestBody Follow follow){
        String userId = follow.getUserId();
        follow.setUserId(userId);
        return followService.save(follow);
    }

    @PostMapping("/update")
    @CrossOrigin
    public Follow update(@RequestBody Follow follow){
        String userId = follow.getUserId();
        follow.setUserId(userId);
        return followService.update(follow);
    }
}
