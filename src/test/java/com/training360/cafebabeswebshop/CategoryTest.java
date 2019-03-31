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
        ResultStatus status = categoryController.createCategoryAndGetId(null);
        List<Category> categoryListNew = categoryController.listCategories();
        // Then
        assertEquals(categoryList.size()+1, categoryListNew.size());
    }
}
