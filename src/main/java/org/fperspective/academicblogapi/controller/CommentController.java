package org.fperspective.academicblogapi.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.fperspective.academicblogapi.model.Comment;
import org.fperspective.academicblogapi.model.Comment;
import org.fperspective.academicblogapi.service.CategoryService;
import org.fperspective.academicblogapi.service.CommentService;
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
@Tag(name = "Comment", description = "Comment Management API")
@RequestMapping("/api/v1/comment")
@CrossOrigin
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(implementation = Comment.class), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class CommentController {

    @Autowired
    // @Lazy
    private CommentService commentService;

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/show")
    @CrossOrigin
    public Collection<Comment> get(){
        return commentService.get();
    }

    @GetMapping("/show/{commentId}")
    @CrossOrigin
    public Comment get(@PathVariable String commentId) {
        Comment comment = commentService.get(commentId);
        if (comment == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return comment;
    }

    @GetMapping("/sort/popular/{blogId}")
    @CrossOrigin
    public List<Comment> findMostPopularComment(@PathVariable String blogId) {
        List<Comment> comment = commentService.findMostPopularComment(blogId);
        if (comment == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return comment;
    }

    @GetMapping("/sort/latest/{blogId}")
    @CrossOrigin
    public List<Comment> sortLatestComment(@PathVariable String blogId) {
        List<Comment> comment = commentService.sortLatestComment(blogId);
        if (comment == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return comment;
    }

    @DeleteMapping("/delete/{commentId}")
    @CrossOrigin
    public void delete(@PathVariable String commentId) {
        commentService.remove(commentId);
    }

    @PostMapping("/show")
    @CrossOrigin
    public Comment save(@RequestBody Comment comment){
        return commentService.save(comment);
    }

    @PostMapping("/update")
    @CrossOrigin
    public Comment update(@RequestBody Comment comment){
        return commentService.update(comment);
    }

    @PostMapping("/like")
    @CrossOrigin
    public Comment like(@RequestBody Comment comment){
        return commentService.like(comment);
    }
    
}
