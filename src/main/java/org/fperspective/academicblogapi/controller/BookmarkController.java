package org.fperspective.academicblogapi.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.fperspective.academicblogapi.model.Blog;
import org.fperspective.academicblogapi.model.Bookmark;
import org.fperspective.academicblogapi.service.BookmarkService;
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
@Tag(name = "Bookmark", description = "Bookmark Management API")
@RequestMapping("/api/v1/bookmark")
@CrossOrigin
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(implementation = Bookmark.class), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class BookmarkController {
    @Autowired
    // @Lazy
    private BookmarkService bookmarkService;

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/show")
    @CrossOrigin
    public Collection<Bookmark> get(){
        return bookmarkService.get();
    }

    @GetMapping("/show/{userId}")
    @CrossOrigin
    public Bookmark get(@PathVariable String userId) {
        Bookmark bookmark = bookmarkService.get(userId);
        if (bookmark == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return bookmark;
    }

    @DeleteMapping("/delete/{userId}")
    @CrossOrigin
    public void delete(@PathVariable String userId) {
        bookmarkService.remove(userId);
    }

    @PostMapping("/show")
    @CrossOrigin
    public Bookmark save(@RequestBody Bookmark bookmark){
        String userId = bookmark.getUserId();
        bookmark.setUserId(userId);
        return bookmarkService.save(bookmark);
    }

    @PostMapping("/bookmark")
    @CrossOrigin
    public Bookmark update(@RequestBody Bookmark bookmark){
        String userId = bookmark.getUserId();
        bookmark.setUserId(userId);
        return bookmarkService.update(bookmark);
    }

    @PostMapping("/unbookmark")
    @CrossOrigin
    public Bookmark unUpdate(@RequestBody Bookmark bookmark){
        String userId = bookmark.getUserId();
        bookmark.setUserId(userId);
        return bookmarkService.unUpdate(bookmark);
    }
}
