package com.ev.logistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ev.logistics.entity.Post;

import java.util.Date;
import java.util.List;

/**
 * @author EV
 * @date 2021/4/30 21:10
 */
public interface PostService extends IService<Post> {
    List<Post> findByUserId(Integer id);

    Post findById(Integer id);

    void updatePostStatusTo1(Integer id, Date date);

    List<Post> findPostBySearch(Post post);
}
