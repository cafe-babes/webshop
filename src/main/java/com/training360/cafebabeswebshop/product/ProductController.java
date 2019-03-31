package com.training360.cafebabeswebshop.product;

import com.training360.cafebabeswebshop.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    private ProductValidator validator;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResultStatus getIncorrectProduct() {
        return new ResultStatus(ResultStatusE.NOT_OK, "Invalid address");
    }


    @GetMapping("/product/{address}")
    public Object getProduct(@PathVariable String address) {
        validator = new ProductValidator(productService);
        List<String> addresses = productService.getProducts().stream().map(p -> p.getAddress()).collect(Collectors.toList());
        if (validator.isValid(address) && addresses.contains(address)) {
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
                return new ResultStatus(ResultStatusE.OK, String.format("Termék sikeresen hozzáadva! (termék id: %d )", id));
            } catch (DataAccessException sql) {
                sql.printStackTrace();
                return new ResultStatus(ResultStatusE.NOT_OK, "Termék cím vagy kód már szerepel másik terméknél");
            }
        } else {
            return new ResultStatus(ResultStatusE.NOT_OK, "Minden adat kitöltendő, maximális ár: 2.000.000 Ft");
        }
    }

    @PostMapping("/products/{id}")
    public ResultStatus updateProducts(@PathVariable long id, @RequestBody Product product) {
        validator = new ProductValidator(productService);
        if (validator.isValidProduct(product)) {
            try {
                productService.updateProducts(id, product);
                return new ResultStatus(ResultStatusE.OK, "Termék sikeresen módosítva!");
            } catch (DataAccessException sql) {
                sql.printStackTrace();
                return new ResultStatus(ResultStatusE.NOT_OK, "Termék cím vagy kód már szerepel másik terméknél");
            }
        } else {
            System.out.println(validator.isValid(product.getCode()));
            System.out.println(validator.isValid(product.getName()));
            System.out.println(validator.isValid(product.getAddress()));
            System.out.println(validator.isValid(product.getManufacture()));
            System.out.println(validator.isValidPrice(product.getPrice()));
            System.out.println(validator.isValid(product.getCategory().getName()));
            return new ResultStatus(ResultStatusE.NOT_OK, "Minden adat kitöltendő, maximális ár: 2.000.000 Ft");
        }
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping("/products/{start}/{size}")
    public List<Product> getProductsWithStartAndSize(@PathVariable int start, @PathVariable int size, @RequestBody(required = false) Category category) {
        if (category==null)
            return productService.getProductsWithStartAndSize(start,size);
        return productService.getProductsWithStartAndSizeAndCategory(start, size, category);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }


    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

}