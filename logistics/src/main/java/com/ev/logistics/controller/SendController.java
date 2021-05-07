package com.ev.logistics.controller;

import com.ev.logistics.entity.Post;
import com.ev.logistics.entity.User;
import com.ev.logistics.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author EV
 * @date 2021/5/4 11:38
 */
@Controller
public class SendController {

    @Autowired
    PostService postService;

    @GetMapping("/toSend")
    public String toSend(HttpSession session, RedirectAttributes attributes){
        User userInfo = (User) session.getAttribute("user");
        if (userInfo != null) {
            return "send";
        } else {
            attributes.addFlashAttribute("message", "权限不足，请先登录");
            return "redirect:/toLogin";
        }
    }

    @PostMapping("/sendPost")
    public String sendPost(Post post,HttpSession session){
        User user = (User) session.getAttribute("user");
        post.setUserId(user.getId());
        post.setCreateTime(new Date());
        post.setUpdateTime(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = dateFormat.format(new Date());
        String num = format.concat(String.valueOf(user.getId()));
        String postNum = num.concat("2");
        post.setNum(postNum);
        postService.save(post);
        return "redirect:/toUserSend";
    }

    @PostMapping("/updatePost")
    public String updatePost(Post post){
        post.setUpdateTime(new Date());
        postService.updateById(post);
        return "redirect:/toUserSend";
    }

}
