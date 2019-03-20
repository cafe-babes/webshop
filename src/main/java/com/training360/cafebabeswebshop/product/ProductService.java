package com.training360.cafebabeswebshop.product;


import org.springframework.stereotype.Service;

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

}
