package com.training360.cafebabeswebshop.order;

import com.training360.cafebabeswebshop.product.ProductService;
import com.training360.cafebabeswebshop.product.ResultStatus;
import com.training360.cafebabeswebshop.product.ResultStatusE;
import com.training360.cafebabeswebshop.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    private OrderValidator validator;

    public OrderController(OrderService orderService, UserService userService, ProductService productService) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
        this.validator = new OrderValidator(orderService,userService,productService);
    }

    @PostMapping("/myorders")
    public ResultStatus saveOrderAndGetId(Authentication authentication){
        try{
                long id = orderService.saveOrderAndGetId(authentication);
                return new ResultStatus(ResultStatusE.OK, String.format("Order successfully created with id %d", id));
        } catch (IllegalStateException e){
            return new ResultStatus(ResultStatusE.NOT_OK, e.getMessage());
        }
    }

    @GetMapping("/myorders")
    public Map<Long, List<OrderedProduct>> listMyOrders(Authentication authentication){
        return orderService.listMyOrders(authentication);
    }

    @GetMapping("/orders")
    public List<Order> listAllOrders(){
        return orderService.listAllOrders();
    }

    @GetMapping("/orders/{id}")
    public List<OrderedProduct> listOrderedProductsByOrderId(@PathVariable long id){
        return orderService.listOrderedProductsByOrderId(id);
    }

    @PostMapping("/orders/{id}")
    public ResultStatus deleteOrder(@PathVariable long id){
        if (validator.isValidOrderId(id)){
            orderService.deleteOrder(id);
            return new ResultStatus(ResultStatusE.OK,String.format("Order successfully deleted with id %d", id));
        } else {
            return new ResultStatus(ResultStatusE.NOT_OK, "Invalid id");
        }
    }

    @DeleteMapping("/orders/{id}/{address}")
    public ResultStatus deleteOneItemFromOrder(@PathVariable long id, @PathVariable String address){
        if (validator.isExistingOrderId(id) && validator.isExistingProductAddress(address)){
            orderService.deleteOneItemFromOrder(id,address);
            return new ResultStatus(ResultStatusE.OK, "Ordered product deleted successfully");
        } else {
            return new ResultStatus(ResultStatusE.NOT_OK, "Invalid id, or address");
        }
    }

}
