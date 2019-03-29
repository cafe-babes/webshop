package com.training360.cafebabeswebshop.category;

import com.training360.cafebabeswebshop.product.ResultStatus;
import com.training360.cafebabeswebshop.product.ResultStatusE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> listCategories() {
        return categoryService.listCategories();
    }

    public ResultStatus createCategoryAndGetId(Category category) {
        try {
            long response = categoryService.createCategoryAndGetId(category);
            if (response==-1)
                return new ResultStatus(ResultStatusE.NOT_OK, "Helytelen sorszám, állítsa be a soron következőt vagy egy már meglévőt");
            return new ResultStatus(ResultStatusE.OK, "Kategória sikeresen hozzáadva!");
        } catch (DataAccessException sql) {
            sql.printStackTrace();
            return new ResultStatus(ResultStatusE.NOT_OK, "Adatbázis ütközés történt!");
        }
    }
}
