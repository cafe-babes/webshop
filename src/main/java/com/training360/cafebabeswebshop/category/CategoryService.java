package com.training360.cafebabeswebshop.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {

    @Autowired
    CategoryDao categoryDao;

    public List<Category> listCategories(){
        return categoryDao.listCategories();
    }

    public long createCategoryAndGetId(Category category) throws DataAccessException {
        long max = categoryDao.getMaxOrdinal();
        System.out.println(category.getOrdinal());
        if (max+1<category.getOrdinal() || category.getOrdinal() < 0){
            return -1;
        }
        if(categoryDao.getCategoryNames().contains(category.getName())){
            return -2;
        }
        if (category.getOrdinal()<=0){
            category.setOrdinal(max + 1);
        }
        else if (category.getOrdinal()<=max)
            while (max >= category.getOrdinal()){
                categoryDao.reindexOrdinal(max--);
            }
        return categoryDao.createCategoryAndGetId(category);
    }

    public long getMaxOrdinal(){
        return categoryDao.getMaxOrdinal();
    }

    public long getMinOrdinal(){
        return categoryDao.getMinOrdinal();
    }

    public void deleteCategory(long id) {
        categoryDao.deleteCategory(id);
    }

    public Object getCategory(String name) {
        return categoryDao.getCategory(name);
    }


    public void updateCategory(long id, Category category) {
        long max = categoryDao.getMaxOrdinal();
        if(category.getOrdinal() <= max){
            while(max >= category.getOrdinal()){
                categoryDao.reindexOrdinal(max--);
            }
        }
        categoryDao.updateCategory(id, category);
    }
}
