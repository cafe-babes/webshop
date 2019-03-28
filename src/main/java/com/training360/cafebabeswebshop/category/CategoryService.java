package com.training360.cafebabeswebshop.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryDao categoryDao;

    public List<Category> listCategories(){
        return categoryDao.listCategories();
    }

    public long createCategoryAndGetId(Category category) {
        return categoryDao.createCategoryAndGetId(category);
    }
}