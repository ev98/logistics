package com.ev.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ev.logistics.entity.Orders;

import java.util.Date;
import java.util.List;

/**
 * @author EV
 * @date 2021/4/30 21:10
 */
public interface OrdersService extends IService<Orders> {
    List<Orders> findByUserId(Integer id);

    void updateOrdersStatusTo1(Integer id, Date date);

    Orders findById(Integer id);

    List<Orders> findAllByUserId(Integer id);
}
