package org.fperspective.academicblogapi.service;

import java.util.Collection;

import org.fperspective.academicblogapi.model.Category;
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

    public Category remove(String categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId).get();
        existingCategory.setStatus(false);
        return categoryRepository.save(existingCategory);
    }

    public Category update(Category category) {
        Category existingCategory = categoryRepository.findById(category.getCategoryId()).get();
        existingCategory.setCategoryName(existingCategory.getCategoryName());
        return categoryRepository.save(existingCategory);
    }
}
