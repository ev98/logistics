package com.ev.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author EV
 * @date 2021/4/30 20:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @TableId
    private Integer id;
    private String cname;

}
