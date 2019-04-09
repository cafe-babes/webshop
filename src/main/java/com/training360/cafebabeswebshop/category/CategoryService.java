package com.training360.cafebabeswebshop.category;

import com.training360.cafebabeswebshop.product.ResultStatus;
import com.training360.cafebabeswebshop.product.ResultStatusEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryDao categoryDao;
    private CategoryValidator categoryValidator;

    public CategoryService(CategoryDao categoryDao, CategoryValidator categoryValidator) {
        this.categoryDao = categoryDao;
        this.categoryValidator = categoryValidator;
    }

    public List<Category> listCategories() {
        return categoryDao.listCategories();
    }

    public ResultStatus createCategoryAndGetId(Category category) {
        long max = categoryDao.getMaxOrdinal();
        if (!categoryValidator.isValidOrdinal(max, category)) {
            return new ResultStatus(ResultStatusEnum.NOT_OK, "Helytelen sorszám, állítsa be a soron következőt vagy egy már meglévőt");
        }
        if(categoryValidator.isExistingCategoryName(category)) {
            return new ResultStatus(ResultStatusEnum.NOT_OK, "Ilyen kategória már létezik, adjon meg egyedi nevet");
        }
        if (category.getOrdinal() == 0) {
            category.setOrdinal(max + 1);
        } else if (category.getOrdinal() <= max) {
            categoryDao.increaseOrdinal(category.getOrdinal());
        }
        long id = categoryDao.createCategoryAndGetId(category);
        return new ResultStatus(ResultStatusEnum.OK, "Kategória sikeresen hozzáadva! id: " + id);
    }

    public long getMaxOrdinal() {
        return categoryDao.getMaxOrdinal();
    }

    public long getMinOrdinal() {
        return categoryDao.getMinOrdinal();
    }

    public int deleteCategory(long id) {
        long ordinal = 0;
        List<Category> categories = categoryDao.listCategories();

        for (Category c : categories) {
            if (c.getId() == id) {
                ordinal = c.getOrdinal();
            }
        }
        int rowCount = categoryDao.deleteCategory(id);
        for (Category category : categories) {
            if (category.getOrdinal() > ordinal) {
                categoryDao.decreaseOrdinal(category.getOrdinal());
            }
        }
        return rowCount;
    }

    public Object getCategory(String name) {
        return categoryDao.getCategory(name);
    }


    public int updateCategory(long id, Category category) {
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
