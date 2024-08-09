package com.ra.projectmd3test.service.impl;

import com.ra.projectmd3test.model.entity.Category;
import com.ra.projectmd3test.repository.design.ICategoryRepository;
import com.ra.projectmd3test.service.design.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    @Override
    public Category findById(Integer id) {
        return null;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteById(Integer t) {

    }
}
