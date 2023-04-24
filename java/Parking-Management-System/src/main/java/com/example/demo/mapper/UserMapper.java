package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    public int countOfUser();
}
