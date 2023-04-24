package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.SysAuth;
import com.example.demo.entity.SysRole;
import com.example.demo.entity.SysUser;
import com.example.demo.mapper.SysUserMapper;
import com.example.demo.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public SysUser findUserByUserName(String username) {
        return this.baseMapper.selectOne(
                new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, username).ne(SysUser::getStatus, "1"));
    }
    @Override
    public List<SysRole> findRoleByUserId(Long userid) {
        return this.baseMapper.findRoleByUserId(userid);
    }

    @Override
    public List<SysAuth> findAuthByUserId(Long userid) {
        return this.baseMapper.findAuthByUserId(userid);
    }


}
