package com.training360.cafebabeswebshop.category;

import com.training360.cafebabeswebshop.product.ResultStatus;
import com.training360.cafebabeswebshop.product.ResultStatusE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> listCategories() {
        return categoryService.listCategories();
    }

    @PostMapping("/categories")
    public ResultStatus createCategoryAndGetId(@RequestBody Category category) {
        if(category.getName().trim().length() == 0){
            return new ResultStatus(ResultStatusE.NOT_OK, "Név megadása kötelező");
        }
        try {
            long response = categoryService.createCategoryAndGetId(category);
            if (response==-1)
                return new ResultStatus(ResultStatusE.NOT_OK, "Helytelen sorszám, állítsa be a soron következőt vagy egy már meglévőt");
            if(response == -2)
                return new ResultStatus(ResultStatusE.NOT_OK, "Ilyen kategória már létezik, adjon meg egyedi nevet");
            return new ResultStatus(ResultStatusE.OK, "Kategória sikeresen hozzáadva!");
        } catch (DataAccessException sql) {
            sql.printStackTrace();
            return new ResultStatus(ResultStatusE.NOT_OK, "Adatbázis ütközés történt!");
        }
    }

    //Todo : productbana  termék státusza null
    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable long id){
        categoryService.deleteCategory(id);
    }
}
