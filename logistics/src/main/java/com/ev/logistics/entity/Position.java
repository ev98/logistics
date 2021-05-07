package com.ev.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author EV
 * @date 2021/4/30 20:55
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    @TableId
    private Integer id;
    private String locker;
    private Integer status;

}
