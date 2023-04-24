package com.example.demo.mapper;

import com.example.demo.entity.SysAuth;
import com.example.demo.entity.SysRole;
import com.example.demo.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author fundebug
 * @since 2021-06-27
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysRole> findRoleByUserId(Long userid);
    List<SysAuth> findAuthByUserId(Long userid);
}
