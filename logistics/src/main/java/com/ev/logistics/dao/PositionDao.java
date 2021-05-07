package com.ev.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ev.logistics.entity.Position;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author EV
 * @date 2021/4/30 21:03
 */
@Mapper
public interface PositionDao extends BaseMapper<Position> {
}
