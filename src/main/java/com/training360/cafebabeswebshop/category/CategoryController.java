package com.training360.cafebabeswebshop.category;

import com.training360.cafebabeswebshop.product.ResultStatus;
import com.training360.cafebabeswebshop.product.ResultStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    private CategoryValidator categoryValidator;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> listCategories() {
        return categoryService.listCategories();
    }

    @PostMapping("/categories")
    public ResultStatus createCategoryAndGetId(@RequestBody Category category) {
        if(category.getName().trim().length() == 0){
            return new ResultStatus(ResultStatusEnum.NOT_OK, "Név megadása kötelező");
        }
        try {
            long response = categoryService.createCategoryAndGetId(category);
            if (response==-1)
                return new ResultStatus(ResultStatusEnum.NOT_OK, "Helytelen sorszám, állítsa be a soron következőt vagy egy már meglévőt");
            if(response == -2)
                return new ResultStatus(ResultStatusEnum.NOT_OK, "Ilyen kategória már létezik, adjon meg egyedi nevet");
            return new ResultStatus(ResultStatusEnum.OK, "Kategória sikeresen hozzáadva!");
        } catch (DataAccessException sql) {
            sql.printStackTrace();
            return new ResultStatus(ResultStatusEnum.NOT_OK, "Adatbázis ütközés történt!");
        }
    }

    @DeleteMapping("/categories/{id}")
    public void deleteCategory(@PathVariable long id){
        categoryService.deleteCategory(id);
    }

    @GetMapping("/categories/{name}")
    public Object getCategory(@PathVariable String name){
        categoryValidator = new CategoryValidator(categoryService);
        List<String> names = categoryService.listCategories().stream().map(p -> p.getName()).collect(Collectors.toList());
        if(categoryValidator.isValidName(name) && names.contains(name)){
            return categoryService.getCategory(name);
        } else {
            return new ResultStatus(ResultStatusEnum.NOT_OK, "Helytelen név");
        }
    }

    @PostMapping("categories/{id}")
    public ResultStatus updateCategory(@PathVariable long id, @RequestBody Category category){
        categoryValidator = new CategoryValidator(categoryService);
        if(categoryValidator.isValidOrder(category.getOrdinal())) {
                categoryService.updateCategory(id, category);
                return new ResultStatus(ResultStatusEnum.OK, "Kategória sikeresen módosítva");
        }
        if(!categoryValidator.isValidName(category.getName())){
            return new ResultStatus(ResultStatusEnum.NOT_OK, "Az adott név már létezik");
        }
        else {
            System.out.println(categoryValidator.isValidName(category.getName()));
            System.out.println(categoryValidator.isValidOrder(category.getOrdinal()));
            return new ResultStatus(ResultStatusEnum.NOT_OK, "az adott sorszámnak a meglévők között kell lennie!");
        }
    }
}