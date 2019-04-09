package com.training360.cafebabeswebshop.dashboard;

import com.training360.cafebabeswebshop.order.OrderDao;
import com.training360.cafebabeswebshop.product.ProductDao;
import com.training360.cafebabeswebshop.user.UserDao;
import org.springframework.stereotype.Service;


@Service
public class DashboardService {
    private UserDao userDao;
    private ProductDao productDao;
    private OrderDao orderDao;

    public DashboardService(UserDao userDao, ProductDao productDao, OrderDao orderDao) {
        this.userDao = userDao;
        this.productDao = productDao;
        this.orderDao = orderDao;
    }

    public Dashboard listOfResult(){
        Dashboard dashboards = new Dashboard(userDao.listUsers().size(),
                productDao.getAllProducts().size(), productDao.getProducts().size(),
                orderDao.listAllOrders().size(), orderDao.listAllActiveOrders().size());
        return dashboards;
    }
}
