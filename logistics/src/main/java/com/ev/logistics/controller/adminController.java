package com.ev.logistics.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ev.logistics.entity.*;
import com.ev.logistics.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author EV
 * @date 2021/5/5 0:15
 */
@Controller
public class adminController {

    @Autowired
    PostService postService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    PositionService positionService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    UserService userService;

    //寄件管理
    @GetMapping("/toAdminSend")
    public String toAdminSend(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, HttpSession session, Model model, RedirectAttributes attributes) {
        User userInfo = (User) session.getAttribute("user");
        if (userInfo != null) {
            PageHelper.startPage(pageNum, 6);
            List<Post> postList = postService.findByUserId(userInfo.getId());
            PageInfo<Post> pageInfo = new PageInfo<>(postList);
            model.addAttribute("postList", postList);
            model.addAttribute("pageInfo", pageInfo);
            List<Category> categoryList = categoryService.list();
            model.addAttribute("categoryList", categoryList);
            return "adminSend";
        } else {
            attributes.addFlashAttribute("message", "权限不足，请先登录");
            return "redirect:/toLogin";
        }
    }

    @GetMapping("adminSendPost/{id}")
    public String adminSendPost(@PathVariable Integer id) {
        postService.updatePostStatusTo1(id, new Date());
        return "redirect:/toAdminSend";
    }

    @PostMapping("/searchPost")
    public String searchPost(Post post, @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, Model model) {
        PageHelper.startPage(pageNum, 6);
        List<Post> postList = postService.findPostBySearch(post);
        PageInfo<Post> pageInfo = new PageInfo<>(postList);
        List<Category> categoryList = categoryService.list();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("postList", postList);
        model.addAttribute("pageInfo", pageInfo);
        return "adminSend";
    }

    //收件管理
    @GetMapping("/toAdminPickUp")
    public String toAdminPickUp(Model model, RedirectAttributes attributes, HttpSession session) {
        User userInfo = (User) session.getAttribute("user");
        if (userInfo != null) {
            QueryWrapper<Position> status0 = new QueryWrapper<Position>().eq("status", 0);
            List<Position> positionList = positionService.list(status0);
            model.addAttribute("positionList", positionList);
            return "adminPickUp";
        } else {
            attributes.addFlashAttribute("message", "权限不足，请先登录");
            return "redirect:/toLogin";
        }
    }

    @PostMapping("/adminPickUpOrders")
    public String adminPickUpOrders(@RequestParam String name, @RequestParam Integer positionId) {
        QueryWrapper<User> userName = new QueryWrapper<User>().eq("name", name);
        User user = userService.getOne(userName);
        Orders orders = new Orders();
        orders.setUserId(user.getId());
        orders.setPositionId(positionId);
        orders.setCreateTime(new Date());
        orders.setUpdateTime(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = dateFormat.format(new Date());
        String num = format.concat(String.valueOf(user.getId()));
        String orderNum = num.concat("1");
        orders.setNum(orderNum);
        ordersService.save(orders);
        //柜子状态变为1
        Position position = positionService.getById(positionId);
        position.setStatus(1);
        positionService.updateById(position);
        return "redirect:/toAdminPickUp";
    }

}
