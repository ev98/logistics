package com.ev.logistics.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author EV
 * @date 2021/4/30 20:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @TableId
    private Integer id;
    private String num;
    private String consignee;
    private String consigneePhone;
    private String consigneeAddress;
    private BigDecimal weight;
    private Date createTime;
    private Date updateTime;
    private Integer status;
    private Integer categoryId;
    private Integer userId;

    private Category category;
    private User user;

}
