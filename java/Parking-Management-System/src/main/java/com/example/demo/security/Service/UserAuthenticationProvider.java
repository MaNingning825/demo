package com.example.demo.security.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.SysUser;
import com.example.demo.security.entity.SysUserDetial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private SysUserDetailService a;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username= (String) authentication.getPrincipal();
        String password= (String) authentication.getCredentials();
        System.out.println("进去了："+username+"-------"+password);
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);

        SysUserDetial sysUserDetial= (SysUserDetial) a.loadUserByUsername(username);
        if(sysUserDetial==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        if(!new BCryptPasswordEncoder().matches(password,sysUserDetial.getPassword())){
            throw new BadCredentialsException("用户名或密码错误");
        }
        if (sysUserDetial.getStatus().equals("2")) {
            throw new LockedException("用户已禁用");
        }
        return new UsernamePasswordAuthenticationToken(sysUserDetial,password,sysUserDetial.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
