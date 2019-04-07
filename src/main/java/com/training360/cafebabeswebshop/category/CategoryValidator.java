package com.training360.cafebabeswebshop.category;

public class CategoryValidator {
    private CategoryService categoryService;

    public CategoryValidator(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public boolean isValidName(String str) {
         return str != null && str.trim().length() != 0;
    }

    public boolean isValidOrder(long ordinal){
        long max = categoryService.getMaxOrdinal();
        long min = categoryService.getMinOrdinal();
        return (max >= ordinal && ordinal >= min);
    }

}
