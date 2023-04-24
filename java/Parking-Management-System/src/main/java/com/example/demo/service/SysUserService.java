package com.example.demo.service;

import com.example.demo.entity.SysAuth;
import com.example.demo.entity.SysRole;
import com.example.demo.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
public interface SysUserService extends IService<SysUser> {
    SysUser findUserByUserName(String username);
    List<SysRole> findRoleByUserId(Long userid);
    List<SysAuth> findAuthByUserId(Long userid);
}
