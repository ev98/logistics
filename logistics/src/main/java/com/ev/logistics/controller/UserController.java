package com.ev.logistics.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ev.logistics.entity.Orders;
import com.ev.logistics.entity.Post;
import com.ev.logistics.entity.User;
import com.ev.logistics.service.OrdersService;
import com.ev.logistics.service.PostService;
import com.ev.logistics.service.UserService;
import com.ev.logistics.util.AliyunOssUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author EV
 * @date 2021/5/3 22:16
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    OrdersService ordersService;

    @Autowired
    private AliyunOssUtil aliyunOssUtil;

    //个人信息
    @GetMapping("/toUserInfo")
    public String toUserInfo(HttpSession session, RedirectAttributes attributes) {

        User userInfo = (User) session.getAttribute("user");
        if (userInfo != null) {
            Integer id = userInfo.getId();
            User user = userService.getById(id);
            //更新
            session.setAttribute("user", user);
            return "userInfo";
        } else {
            attributes.addFlashAttribute("message", "权限不足，请先登录");
            return "redirect:/toLogin";
        }
    }

    @PostMapping("/updateUserInfo")
    public String updateUserInfo(User user, HttpSession session) {
        User userInfo = (User) session.getAttribute("user");
        Integer id = userInfo.getId();
        user.setId(id);
        userService.updateById(user);
        return "redirect:/toUserInfo";
    }

    @GetMapping("/findByName")
    @ResponseBody
    public void findName(String name, HttpSession session, HttpServletResponse response) throws IOException {
        User nowUser = (User) session.getAttribute("user");
        Integer id = nowUser.getId();
        response.setContentType("application/json;charset=utf-8");
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("name", name).ne("id", id);
        User userInfo = userService.getOne(wrapper);
        Map<String, Object> map = new HashMap<>();
        if (userInfo != null) {
            map.put("nameExit", true);
            map.put("msg", "用户名已存在");
        } else {
            map.put("nameExit", false);
            map.put("msg", "正确");
        }
        //将map转为json，并且传递给客户端
        String s = JSON.toJSONString(map);
        PrintWriter writer = response.getWriter();
        writer.write(s);
        writer.close();
    }

    @RequestMapping("/uploadAvatar")
    public String uploadAvatar(@RequestParam("file") MultipartFile file, HttpSession session) {
        String filename = file.getOriginalFilename();
        try {
            if (file != null) {
                if (!"".equals(filename.trim())) {
                    File newFile = new File(filename);
                    FileOutputStream outputStream = new FileOutputStream(newFile);
                    outputStream.write(file.getBytes());
                    outputStream.close();
                    file.transferTo(newFile);
                    //返回图片的URL
                    String url = aliyunOssUtil.upLoad(newFile);
                    User userInfo = (User) session.getAttribute("user");
                    userInfo.setAvatar(url);
                    userService.saveOrUpdate(userInfo);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/toUserInfo";
    }


    //我收到的
    @GetMapping("/toUserPickUp")
    public String toUserPickUp(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, HttpSession session, Model model, RedirectAttributes attributes) {
        User userInfo = (User) session.getAttribute("user");
        if (userInfo != null) {
            PageHelper.startPage(pageNum, 6);
            List<Orders> ordersList = ordersService.findAllByUserId(userInfo.getId());
            PageInfo<Orders> pageInfo = new PageInfo<>(ordersList);
            model.addAttribute("ordersList", ordersList);
            model.addAttribute("pageInfo", pageInfo);
            return "userPickUp";
        } else {
            attributes.addFlashAttribute("message", "权限不足，请先登录");
            return "redirect:/toLogin";
        }
    }

    //我寄出的
    @GetMapping("/toUserSend")
    public String toUserSend(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, HttpSession session, Model model, RedirectAttributes attributes) {
        User userInfo = (User) session.getAttribute("user");
        if (userInfo != null) {
            PageHelper.startPage(pageNum, 6);
            List<Post> postList = postService.findByUserId(userInfo.getId());
            PageInfo<Post> pageInfo = new PageInfo<>(postList);
            model.addAttribute("postList", postList);
            model.addAttribute("pageInfo", pageInfo);
            return "userSend";
        } else {
            attributes.addFlashAttribute("message", "权限不足，请先登录");
            return "redirect:/toLogin";
        }
    }

    @GetMapping("toPostDetail/{id}")
    public String toPostDetail(@PathVariable Integer id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post",post);
        return "send3";
    }

    @GetMapping("toUpdatePost/{id}")
    public String toUpdatePost(@PathVariable Integer id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post",post);
        return "send2";
    }

    @GetMapping("deletePost/{id}")
    public String deletePost(@PathVariable Integer id) {
        postService.removeById(id);
        return "redirect:/toUserSend";
    }

}
