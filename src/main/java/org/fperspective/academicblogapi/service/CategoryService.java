package org.fperspective.academicblogapi.service;

import java.util.Collection;

import org.fperspective.academicblogapi.model.Category;
import org.fperspective.academicblogapi.repository.CategoryRepository;
import org.fperspective.academicblogapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public Collection<Category> get() {
        return categoryRepository.findAll();
    }

    public Category get(String categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public void remove(String categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
