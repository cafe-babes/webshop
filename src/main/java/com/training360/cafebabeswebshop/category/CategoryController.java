package com.training360.cafebabeswebshop.category;

import com.training360.cafebabeswebshop.product.ResultStatus;
import com.training360.cafebabeswebshop.product.ResultStatusEnum;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    private CategoryValidator categoryValidator;

    public CategoryController(CategoryService categoryService, CategoryValidator categoryValidator) {
        this.categoryService = categoryService;
        this.categoryValidator = categoryValidator;
    }

    @GetMapping("/categories")
    public List<Category> listCategories() {
        return categoryService.listCategories();
    }

    @PostMapping("/categories")
    public ResultStatus createCategoryAndGetId(@RequestBody Category category) {
        if(category.getName() == null || category.getName().trim().length() == 0){
            return new ResultStatus(ResultStatusEnum.NOT_OK, "Név megadása kötelező");
        }
        try {
            return categoryService.createCategoryAndGetId(category);
        } catch (DataAccessException sql) {
            sql.printStackTrace();
        }
        return new ResultStatus(ResultStatusEnum.NOT_OK, "Hiba történt");
    }

    @DeleteMapping("/categories/{id}")
    public ResultStatus deleteCategory(@PathVariable long id){
        if(categoryService.deleteCategory(id) >= 0)
            return new ResultStatus(ResultStatusEnum.OK, "Sikeres törlés!");
        else
            return new ResultStatus(ResultStatusEnum.NOT_OK, "Sikertelen törlés!");
    }

    @GetMapping("/categories/{name}")
    public Object getCategory(@PathVariable String name){
        List<String> names = categoryService.listCategories().stream().map(Category::getName).collect(Collectors.toList());
        if(categoryValidator.isValidName(name) && names.contains(name)){
            return categoryService.getCategory(name);
        } else {
            return new ResultStatus(ResultStatusEnum.NOT_OK, "Helytelen név");
        }
    }

    @PostMapping("categories/{id}")
    public ResultStatus updateCategory(@PathVariable long id, @RequestBody Category category){
        if(!categoryValidator.isValidName(category.getName())){
            return new ResultStatus(ResultStatusEnum.NOT_OK, "Üres név");
        } else if(categoryValidator.isValidOrder(category.getOrdinal())) {
            try {
               if(categoryService.updateCategory(id, category) == 0){
                   return new ResultStatus(ResultStatusEnum.NOT_OK, "Nem változott semmi, lehet hogy üres a név");
               }
                return new ResultStatus(ResultStatusEnum.OK, "Kategória sikeresen módosítva");
            } catch (DataAccessException dae){
                return new ResultStatus(ResultStatusEnum.NOT_OK, "Az adott név már létezik");
            }
        } else {
            return new ResultStatus(ResultStatusEnum.NOT_OK, "az adott sorszámnak a meglévők között kell lennie!");
        }
    }
}