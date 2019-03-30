package com.training360.cafebabeswebshop.category;

import org.springframework.beans.factory.annotation.Autowired;

public class CategoryValidator {
    private CategoryService categoryService;

    public CategoryValidator(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public boolean isValidName(String str) {
         return categoryService.listCategories().contains(str) || str != null && str.trim().length() != 0;
    }

    public boolean isValidOrder(long ordinal){
        long max = categoryService.getMaxOrdinal();
        long min = categoryService.getMinOrdinal();
        return (max >= ordinal && ordinal >= min);
    }

}
