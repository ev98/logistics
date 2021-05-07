package com.ev.logistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ev.logistics.dao.CategoryDao;
import com.ev.logistics.entity.Category;
import com.ev.logistics.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @author EV
 * @date 2021/4/30 21:13
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {
}
