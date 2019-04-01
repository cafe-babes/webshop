package com.training360.cafebabeswebshop;

import com.training360.cafebabeswebshop.category.Category;
import com.training360.cafebabeswebshop.category.CategoryController;
import com.training360.cafebabeswebshop.product.ResultStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "/init.sql")
public class CategoryTest {

    @Autowired
    private CategoryController categoryController;

    @Test
    public void testListCategories() {
        // Given
        // When
        List<Category> categoryList = categoryController.listCategories();
        // Then
        assertEquals(3, categoryList.size());
        assertEquals("pretty", categoryList.get(0).getName());
        assertEquals(3, categoryList.get(2).getOrdinal());
    }

    @Test
    public void testCreate() {
        // Given
        List<Category> categoryList = categoryController.listCategories();
        // When
        ResultStatus status1 = categoryController.createCategoryAndGetId(new Category(4, "insertToEnd", 4));
        ResultStatus status2 = categoryController.createCategoryAndGetId(new Category(5, "insertFirst", 1));
        ResultStatus status3 = categoryController.createCategoryAndGetId(new Category(6, "insertZero", 0));
        ResultStatus status4 = categoryController.createCategoryAndGetId(new Category(7, "insertToSecond", 2));
        List<Category> categoryListNew = categoryController.listCategories();
        // Then
        assertEquals(categoryList.size()+1, categoryListNew.size());
    }
}
