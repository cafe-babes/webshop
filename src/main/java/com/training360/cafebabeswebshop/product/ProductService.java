package com.training360.cafebabeswebshop.product;


import com.training360.cafebabeswebshop.category.Category;
import com.training360.cafebabeswebshop.category.CategoryDao;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ProductService {


    private ProductDao productDao;
    private CategoryDao categoryDao;

    public ProductService(ProductDao productDao, CategoryDao categoryDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
    }

    public List<Product> getProducts(){
        return productDao.getProducts();
    }

    public List<Product> getProductsWithStartAndSize(int start, int size){
        return productDao.getProductsWithStartAndSize(start, size);
    }

    public long saveProductAndGetId(Product product) throws DataAccessException {
        for (Category category:categoryDao.listCategories()) {
            if (category.getName().equals(product.getCategory().getName()))
        return productDao.saveProductAndGetId(product);
        }
        return categoryDao.createCategoryAndGetId(product.getCategory());//TODO
    }

    public void updateProducts(long id, Product product) throws DataAccessException {
        productDao.updateProduct(id, product);
    }

    public void deleteProduct(long id){
        productDao.deleteProduct(id);
    }

    public Product findById(@PathVariable long id){
        return productDao.findById(id);
    }

}
