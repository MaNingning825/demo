package com.example.demo.service;

import com.example.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
public interface UserService extends IService<User> {
    int countOfUser();
}
