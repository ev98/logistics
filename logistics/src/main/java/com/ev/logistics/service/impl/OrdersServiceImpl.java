package com.ev.logistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ev.logistics.dao.OrdersDao;
import com.ev.logistics.entity.Orders;
import com.ev.logistics.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author EV
 * @date 2021/4/30 21:13
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersDao, Orders> implements OrdersService {

    @Autowired
    OrdersDao ordersDao;

    @Override
    public List<Orders> findByUserId(Integer id) {
        return ordersDao.findByUserId(id);
    }

    @Override
    public void updateOrdersStatusTo1(Integer id, Date date) {
        ordersDao.updateOrdersStatusTo1(id,date);
    }

    @Override
    public Orders findById(Integer id) {
        return ordersDao.findById(id);
    }

    @Override
    public List<Orders> findAllByUserId(Integer id) {
        return ordersDao.findAllByUserId(id);
    }
}
