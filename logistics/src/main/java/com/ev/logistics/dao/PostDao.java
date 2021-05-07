package com.ev.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ev.logistics.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author EV
 * @date 2021/4/30 21:03
 */
@Mapper
public interface PostDao extends BaseMapper<Post> {

    List<Post> findByUserId(@Param("id") Integer id);

    Post findById(@Param("id") Integer id);

    void updatePostStatusTo1(@Param("id") Integer id, @Param("date") Date date);

    List<Post> findPostBySearch(Post post);
}
