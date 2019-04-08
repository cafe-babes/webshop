
package com.training360.cafebabeswebshop.order;

import com.training360.cafebabeswebshop.product.Product;
import com.training360.cafebabeswebshop.product.ProductService;
import com.training360.cafebabeswebshop.user.User;
import com.training360.cafebabeswebshop.user.UserService;

public class OrderValidator {

    private OrderService orderService;
    private UserService userService;
    private ProductService productService;

    public OrderValidator(OrderService orderService, UserService userService, ProductService productService) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
    }

    public boolean isValidOrder(Order order){
        return isValidUserId(order.getUserId()) &&
                isValidTotal(order.getTotal());
    }

    public boolean isValidOrderId(long id){
        for (Order o: orderService.listAllOrders()) {
            if (o.getId() == id){
                return true;

            }
        }
        return false;
    }

    public boolean isExistingOrderId(long id){
        for (OrderedProduct op: orderService.listAllOrderedProduct()) {
            if (op.getOrderId() == id){
                return true;
            }
        }
        return false;
    }

    public boolean isExistingProductAddress(String address){
        boolean presentAddress = false;
        for (Product p: productService.getProducts()) {
            if (p.getAddress().equals(address)){
                presentAddress = true;
            }
        }
        if (address != null && !address.trim().equals("") && presentAddress){
            return true;
        }
        return false;
    }

    public boolean isValidStatus(String status){
        for (OrderStatus os: OrderStatus.values()) {
            if (os.name().equals(status)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidUserId(long id){
        boolean presentUserId = false;

        for (User u: userService.listUsers()) {
            if (u.getId() == id){
                presentUserId = true;
            }
        }
        return (id > 0 && presentUserId);
    }

    private boolean isValidTotal(long total){
        return (total >= 0);
    }
}
