package com.ev.logistics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ev.logistics.dao.PostDao;
import com.ev.logistics.entity.Post;
import com.ev.logistics.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author EV
 * @date 2021/4/30 21:14
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostDao, Post> implements PostService {

    @Autowired
    PostDao postDao;

    @Override
    public List<Post> findByUserId(Integer id) {
        return postDao.findByUserId(id);
    }

    @Override
    public Post findById(Integer id) {
        return postDao.findById(id);
    }

    @Override
    public void updatePostStatusTo1(Integer id, Date date) {
        postDao.updatePostStatusTo1(id,date);
    }

    @Override
    public List<Post> findPostBySearch(Post post) {
        return postDao.findPostBySearch(post);
    }
}
