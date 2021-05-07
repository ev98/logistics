package com.ev.logistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ev.logistics.dao.UserDao;
import com.ev.logistics.entity.User;
import com.ev.logistics.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author EV
 * @date 2021/4/30 21:14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
