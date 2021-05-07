package com.ev.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ev.logistics.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author EV
 * @date 2021/4/30 21:03
 */
@Mapper
public interface OrdersDao extends BaseMapper<Orders> {

    List<Orders> findByUserId(@Param("id") Integer id);

    void updateOrdersStatusTo1(@Param("id") Integer id, @Param("date") Date date);

    Orders findById(@Param("id") Integer id);

    List<Orders> findAllByUserId(@Param("id") Integer id);
}
