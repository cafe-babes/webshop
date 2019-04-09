package com.training360.cafebabeswebshop.category;

import org.springframework.stereotype.Component;

@Component
public class CategoryValidator {
    private CategoryDao categoryDao;

    public CategoryValidator(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public boolean isValidName(String str) {
         return str != null && str.trim().length() != 0;
    }

    public boolean isValidOrder(long ordinal){
        long max = categoryDao.getMaxOrdinal();
        long min = categoryDao.getMinOrdinal();
        return (max >= ordinal && ordinal >= min);
    }

    public boolean isValidOrdinal(long max, Category category) {
        return max + 1 >= category.getOrdinal() && category.getOrdinal() >= 0;
    }

    public boolean isExistingCategoryName(Category category) {
        return !categoryDao.getCategoryByName(category.getName()).isEmpty();
    }
}
