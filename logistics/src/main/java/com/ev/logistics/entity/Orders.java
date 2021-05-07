package com.ev.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author EV
 * @date 2021/4/30 20:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @TableId
    private Integer id;
    private String num;
    private Integer status;
    private Date createTime;
    private Date updateTime;
    private Integer positionId;
    private Integer userId;

    private Position position;
    private User user;

}
