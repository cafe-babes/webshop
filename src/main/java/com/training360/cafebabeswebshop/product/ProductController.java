package com.training360.cafebabeswebshop.product;

import com.training360.cafebabeswebshop.user.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    private ProductValidator validator;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{address}")
    public Object getProduct(@PathVariable String address) {
        validator = new ProductValidator(productService);
        if (validator.isValidAddress(address)) {
            return productService.getProduct(address);
        } else {
            return new ResultStatus(ResultStatusE.NOT_OK, "Invalid address");
        }
    }

    @PostMapping("/products")
    public ResultStatus saveProductAndGetId(@RequestBody Product product) {
        validator = new ProductValidator(productService);
        if (validator.isValidProduct(product)) {
            try {
                long id = productService.saveProductAndGetId(product);
                return new ResultStatus(ResultStatusE.OK, String.format("Product successfully created with id %d", id));
            } catch (DataAccessException sql) {
                return new ResultStatus(ResultStatusE.NOT_OK, "Product address or code already in use");
            }
        } else {
            return new ResultStatus(ResultStatusE.NOT_OK, "Create not successful!");
        }
    }

    @PostMapping("/products/{id}")
    public ResultStatus updateProducts(@PathVariable long id, @RequestBody Product product) {
        validator = new ProductValidator(productService);
        if (validator.isValidProduct(product)) {
            try {
                productService.updateProducts(id, product);
                return new ResultStatus(ResultStatusE.OK, "Product successfully updated");
            } catch (DataAccessException sql) {
                return new ResultStatus(ResultStatusE.NOT_OK, "Product address or code already in use");
            }
        } else {
            return new ResultStatus(ResultStatusE.NOT_OK, "Update not successful!");
        }
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getActiveProducts();
    }

    @GetMapping("/products/{start}/{size}")
    public List<Product> getProductsWithStartAndSize(@PathVariable int start, @PathVariable int size) {
        return productService.getProductsWithStartAndSize(start, size);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }


    @GetMapping("/products/{id}")
    public Product findById(@PathVariable long id) {
        return productService.findById(id);
    }

}