package com.example.jqueryajax.service;

import com.example.jqueryajax.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();

    Optional<Category> findById(long id);

    Category update(Category category);

    void delete(long id);
}
