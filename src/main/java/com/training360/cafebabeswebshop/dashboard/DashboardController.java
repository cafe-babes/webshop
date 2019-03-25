package com.training360.cafebabeswebshop.dashboard;

import com.training360.cafebabeswebshop.order.OrderService;
import com.training360.cafebabeswebshop.product.ProductService;
import com.training360.cafebabeswebshop.user.User;
import com.training360.cafebabeswebshop.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    //countAllUsers
    @GetMapping("/dashboard")
    public List<Integer> listOfResults(){
        return dashboardService.listOfResults();
    }

}
