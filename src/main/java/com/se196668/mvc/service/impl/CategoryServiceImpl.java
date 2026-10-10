package com.se196668.mvc.service.impl;

import com.se196668.mvc.entity.Category;
import com.se196668.mvc.repository.CategoryRepository;
import com.se196668.mvc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public HashMap<Integer, String> cateList() {
        HashMap<Integer, String> result = new HashMap<>();
        List<Category> list = categoryRepository.findAll();
        for(Category c: list){
            result.put(c.getId(), c.getCategoryName());
        }
        return result;
    }
}
