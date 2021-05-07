package com.ev.logistics.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ev.logistics.entity.User;
import com.ev.logistics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author EV
 * @date 2021/4/8 23:31
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/userLogin")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("username", username).eq("password", password);
        User user = userService.getOne(wrapper);
        if (user != null){
            session.setAttribute("user",user);
            return "redirect:/index";
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/toLogin";
        }
    }

    @GetMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        user.setCreateTime(new Date());
        userService.save(user);
        return "redirect:/toLogin";
    }

    @GetMapping("/findUsername")
    @ResponseBody
    public void findUsername(String username, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("username", username);
        User userInfo = userService.getOne(wrapper);
        Map<String, Object> map = new HashMap<>();
        if (userInfo != null) {
            map.put("userExit", true);
            map.put("msg", "用户名已存在");
        } else {
            map.put("userExit", false);
            map.put("msg", "正确");
        }
        //将map转为json，并且传递给客户端
        String s = JSON.toJSONString(map);
        PrintWriter writer = response.getWriter();
        writer.write(s);
        writer.close();
    }

    @GetMapping("/findName")
    @ResponseBody
    public void findName(String name, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("name", name);
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

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/toLogin";
    }

}
