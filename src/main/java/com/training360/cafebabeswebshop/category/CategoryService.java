package com.training360.cafebabeswebshop.category;

import com.training360.cafebabeswebshop.product.ResultStatus;
import com.training360.cafebabeswebshop.product.ResultStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryDao categoryDao;

    public List<Category> listCategories() {
        return categoryDao.listCategories();
    }

    public long createCategoryAndGetId(Category category) throws DataAccessException {
        long max = categoryDao.getMaxOrdinal();
        if (max + 1 < category.getOrdinal() || category.getOrdinal() < 0) {
            return -1;
        }

        if (category.getOrdinal() <= 0) {
            category.setOrdinal(max + 1);
        } else if (category.getOrdinal() <= max) {
            while (max >= category.getOrdinal()) {
                categoryDao.increaseOrdinal(max--);
            }
        }
        return categoryDao.createCategoryAndGetId(category);
    }

    public long getMaxOrdinal() {
        return categoryDao.getMaxOrdinal();
    }

    public long getMinOrdinal() {
        return categoryDao.getMinOrdinal();
    }

    public void deleteCategory(long id) {
        long ordinal = 0;
        List<Category> categories = categoryDao.listCategories();

        for (Category c : categories) {
            if (c.getId() == id) {
                ordinal = c.getOrdinal();
            }
        }
        categoryDao.deleteCategory(id);
        for (Category category : categories) {
            if (category.getOrdinal() > ordinal) {
                categoryDao.decreaseOrdinal(category.getOrdinal());
            }
        }
    }

    public Object getCategory(String name) {
        return categoryDao.getCategory(name);
    }


    public int updateCategory(long id, Category category) throws DataAccessException {
        List<Category> categories = categoryDao.listCategories();
        long originalOrdinal = category.getOrdinal();

        for (Category c : categories) {
            if (c.getId() == id) {
                originalOrdinal = c.getOrdinal();
            }
        }

        if (originalOrdinal > category.getOrdinal()) {
            for (int i = categories.size() - 1; i >= 0; i--) {
                if (categories.get(i).getOrdinal() >= category.getOrdinal() && categories.get(i).getOrdinal() < originalOrdinal) {
                    categoryDao.increaseOrdinal(categories.get(i).getOrdinal());
                }
            }
        }
        else if (category.getOrdinal() > originalOrdinal) {
            for (int i = 0; i < categories.size(); i++) {
                if (categories.get(i).getOrdinal() <= category.getOrdinal() && categories.get(i).getOrdinal() > originalOrdinal) {
                    categoryDao.decreaseOrdinal(categories.get(i).getOrdinal());
                }
            }
        }
        return categoryDao.updateCategory(id, category);
    }
}
