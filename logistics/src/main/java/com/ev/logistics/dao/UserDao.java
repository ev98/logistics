package com.ev.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ev.logistics.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author EV
 * @date 2021/4/30 21:02
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}
