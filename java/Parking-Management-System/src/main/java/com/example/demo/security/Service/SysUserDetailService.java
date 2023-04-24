package com.example.demo.security.Service;

import com.example.demo.entity.SysRole;
import com.example.demo.entity.SysUser;
import com.example.demo.security.entity.SysUserDetial;
import com.example.demo.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysUserDetailService implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("-----------------"+username);
        SysUser sysUser=sysUserService.findUserByUserName(username);
        System.out.println("------------------------"+sysUser.getUsername());
        if(sysUser!=null){
            SysUserDetial sysUserDetial=new SysUserDetial();
            BeanUtils.copyProperties(sysUser,sysUserDetial);
            Set<GrantedAuthority> authoritySet=new HashSet<>();
            List<SysRole> roles= sysUserService.findRoleByUserId(sysUserDetial.getId());
            for(SysRole sysRole: roles){
                authoritySet.add(new SimpleGrantedAuthority("ROLE_"+sysRole.getRoleName()));
            }
            sysUserDetial.setAuthorities(authoritySet);
            return sysUserDetial;
        }
        return null;
    }
}
