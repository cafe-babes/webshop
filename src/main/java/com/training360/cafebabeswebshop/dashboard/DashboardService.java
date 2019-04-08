package com.training360.cafebabeswebshop.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private DashboardDao dashboardDao;


    public List<Integer> listOfResults() {
        List<Integer> list = new ArrayList<>();
        list.add(dashboardDao.countAllUsers());
        list.add(dashboardDao.countAllProducts());
        list.add(dashboardDao.countAllActiveProducts());
        list.add(dashboardDao.countAllOrders());
        list.add(dashboardDao.countAllActiveOrders());
        return list;
    }
}
