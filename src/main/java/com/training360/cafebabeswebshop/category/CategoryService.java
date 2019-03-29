package com.training360.cafebabeswebshop.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryDao categoryDao;

    public List<Category> listCategories(){
        return categoryDao.listCategories();
    }

    public long createCategoryAndGetId(Category category) throws DataAccessException {
        long max = categoryDao.getMaxOrdinal();
        if (max+1<category.getOrdinal())
            return -1;
        if (category.getOrdinal()<=0)
            category.setOrdinal(max+1);
        else if (category.getOrdinal()<=max)
            while (max >= category.getOrdinal()) {
                categoryDao.reindexOrdinal(max--);
            }
        return categoryDao.createCategoryAndGetId(category);
    }
}
