package com.training360.cafebabeswebshop.product;

import com.training360.cafebabeswebshop.products.Product;

public class ProductService {
    ProductDao productDao;

    public Product getProduct() {
        return productDao.getProduct();
    }
}
