package com.training360.cafebabeswebshop.product;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ProductService {


    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product getProduct(String address) {
        return productDao.getProduct(address);
    }

    public List<Product> getProducts(){
        return productDao.getProducts();
    }


    public long saveProductAndGetId(Product product){
        return productDao.saveProductAndGetId(product);
    }

    public void updateProducts(long id, Product product) {
        productDao.updateProduct(id, product);
    }

    public void deleteProduct(long id){
        productDao.deleteProduct(id);
    }

    public Product findById(@PathVariable long id){
        return productDao.findById(id);
    }

}
