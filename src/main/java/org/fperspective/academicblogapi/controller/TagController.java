package org.fperspective.academicblogapi.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.fperspective.academicblogapi.model.BTag;
import org.fperspective.academicblogapi.service.TagService;
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
@Tag(name = "Tag", description = "Tag Management API")
@RequestMapping("/api/v1/tag")
@CrossOrigin
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(implementation = Tag.class), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class TagController {
    
    @Autowired
    private TagService tagService;

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/show")
    @CrossOrigin
    public Collection<BTag> get(){
        return tagService.get();
    }

    @GetMapping("/show/{tagId}")
    @CrossOrigin
    public BTag get(@PathVariable String tagId) {
        BTag tag = tagService.get(tagId);
        if (tag == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return tag;
    }

    @GetMapping("/sort/most")
    @CrossOrigin
    //Show most popular tags and its used count as Hashmap
    public Map<BTag, Integer> search() {
        Map<BTag, Integer> tags = tagService.findMostUsedTag();
        if (tags == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return tags;
    }


    @DeleteMapping("/delete/{tagId}")
    @CrossOrigin
    public void delete(@PathVariable String tagId) {
        tagService.remove(tagId);
    }

    @PostMapping("/show")
    @CrossOrigin
    public BTag save(@RequestBody BTag tag){
        return tagService.save(tag);
    }

}
