package com.ev.logistics.controller;

import com.ev.logistics.entity.Orders;
import com.ev.logistics.entity.Position;
import com.ev.logistics.entity.Post;
import com.ev.logistics.entity.User;
import com.ev.logistics.service.OrdersService;
import com.ev.logistics.service.PositionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author EV
 * @date 2021/5/5 1:39
 */
@Controller
public class PickUpController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    PositionService positionService;

    @GetMapping("/toPickUp")
    public String toPickUp(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, Model model, HttpSession session, RedirectAttributes attributes) {
        User userInfo = (User) session.getAttribute("user");
        if (userInfo != null) {
            PageHelper.startPage(pageNum, 6);
            List<Orders> ordersList = ordersService.findByUserId(userInfo.getId());
            PageInfo<Orders> pageInfo = new PageInfo<>(ordersList);
            model.addAttribute("ordersList",ordersList);
            model.addAttribute("pageInfo",pageInfo);
            return "pickUp";
        } else {
            attributes.addFlashAttribute("message", "权限不足，请先登录");
            return "redirect:/toLogin";
        }
    }

    @GetMapping("/pickUpOrders/{id}")
    public String pickUpOrders(@PathVariable Integer id){
        ordersService.updateOrdersStatusTo1(id,new Date());
        //柜子状态变为0
        Orders orders = ordersService.findById(id);
        Position position = positionService.getById(orders.getPositionId());
        position.setStatus(0);
        positionService.updateById(position);
        return "redirect:/toPickUp";
    }

    @GetMapping("/pickUpOrders2/{id}")
    public String pickUpOrders2(@PathVariable Integer id){
        ordersService.updateOrdersStatusTo1(id,new Date());
        //柜子状态变为0
        Orders orders = ordersService.findById(id);
        Position position = positionService.getById(orders.getPositionId());
        position.setStatus(0);
        positionService.updateById(position);
        return "redirect:/toUserPickUp";
    }

}
