package org.fperspective.academicblogapi.controller;

import java.io.IOException;
import java.util.Collection;

import org.fperspective.academicblogapi.model.Category;
import org.fperspective.academicblogapi.service.CategoryService;
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
@Tag(name = "Category", description = "Category Management API")
@RequestMapping("/api/v1/category")
@CrossOrigin
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(implementation = Category.class), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class CategoryController {
    
    @Autowired
    // @Lazy
    private CategoryService categoryService;

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("/show")
    @CrossOrigin
    public Collection<Category> get(){
        return categoryService.get();
    }

    @GetMapping("/show/{categoryId}")
    @CrossOrigin
    public Category get(@PathVariable String categoryId) {
        Category category = categoryService.get(categoryId);
        if (category == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return category;
    }

    @DeleteMapping("/delete/{categoryId}")
    @CrossOrigin
    public void delete(@PathVariable String categoryId) {
        categoryService.remove(categoryId);
    }

    @PostMapping("/show")
    @CrossOrigin
    public Category save(@RequestBody Category category){
        category.setStatus(false);
        return categoryService.save(category);
    }

    @PostMapping("/update")
    @CrossOrigin
    public Category update(@RequestBody Category category){
        return categoryService.update(category);
    }
}
