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
import java.util.stream.Collectors;

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
        List<String> names = categoryListNew.stream().map(Category::getName).collect(Collectors.toList());
        List<Long> ordinals = categoryListNew.stream().map(Category::getOrdinal).collect(Collectors.toList());
        List<String> results = List.of(status1.getStatus().toString(), status2.getStatus().toString(), status3.getStatus().toString(), status4.getStatus().toString());
        // Then
        assertEquals(categoryList.size()+4, categoryListNew.size());
        assertEquals(List.of("insertFirst", "insertToSecond", "pretty", "fast", "smart", "insertToEnd", "insertZero"), names);
        assertEquals(List.of(1L,2L,3L,4L,5L,6L,7L), ordinals);
        assertEquals(results, List.of("OK","OK","OK","OK"));
    }

    @Test
    public void testCreateError() {
        // Given
        // When
        ResultStatus status1 = categoryController.createCategoryAndGetId(new Category(4, "", 4));
        ResultStatus status2 = categoryController.createCategoryAndGetId(new Category(5, "imABadBoy", 6));
        ResultStatus status3 = categoryController.createCategoryAndGetId(new Category(6, "insertNegative", -5));
        ResultStatus status4 = categoryController.createCategoryAndGetId(new Category(7, "pretty", 2));
        // Then
        assertEquals("Név megadása kötelező", status1.getMessage());
        assertEquals("Helytelen sorszám, állítsa be a soron következőt vagy egy már meglévőt", status2.getMessage());
        assertEquals("Helytelen sorszám, állítsa be a soron következőt vagy egy már meglévőt", status3.getMessage());
        assertEquals("Ilyen kategória már létezik, adjon meg egyedi nevet", status4.getMessage());

    }
}
