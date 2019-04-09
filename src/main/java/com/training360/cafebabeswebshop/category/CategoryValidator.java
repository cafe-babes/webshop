package com.training360.cafebabeswebshop.category;

import org.springframework.dao.EmptyResultDataAccessException;

public class CategoryValidator {
    private CategoryService categoryService;
    private CategoryDao categoryDao;

    public CategoryValidator(CategoryService categoryService, CategoryDao categoryDao) {
        this.categoryService = categoryService;
        this.categoryDao = categoryDao;
    }

    public boolean isValidName(String str) {
         return str != null && str.trim().length() != 0;
    }

    public boolean isValidOrder(long ordinal){
        long max = categoryService.getMaxOrdinal();
        long min = categoryService.getMinOrdinal();
        return (max >= ordinal && ordinal >= min);
    }

    public boolean isValidOrdinal(long max, Category category) {
        return max + 1 > category.getOrdinal() || category.getOrdinal() > 0;
    }

    public boolean isExistingCategoryName(Category category) {
        try {
            categoryDao.getCategoryByName(category.getName());
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
