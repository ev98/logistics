package com.ev.logistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ev.logistics.dao.PositionDao;
import com.ev.logistics.entity.Position;
import com.ev.logistics.service.PositionService;
import org.springframework.stereotype.Service;

/**
 * @author EV
 * @date 2021/4/30 21:14
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionDao, Position> implements PositionService {
}
