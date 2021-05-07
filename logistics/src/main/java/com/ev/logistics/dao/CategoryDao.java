package com.ev.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ev.logistics.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author EV
 * @date 2021/4/30 21:03
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {
}
