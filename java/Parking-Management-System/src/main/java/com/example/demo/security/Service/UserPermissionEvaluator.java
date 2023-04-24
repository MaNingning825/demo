package com.example.demo.security.Service;

import com.example.demo.entity.SysAuth;
import com.example.demo.entity.SysUser;
import com.example.demo.security.entity.SysUserDetial;
import com.example.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private SysUserService sysUserService;
    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        SysUserDetial sysUserDetial=(SysUserDetial)authentication.getPrincipal();
        Set<String> permissions = new HashSet<>();
        List<SysAuth> authList = sysUserService.findAuthByUserId(sysUserDetial.getId());
        for(SysAuth sysAuth : authList){
            permissions.add(sysAuth.getPermission());
        }
        if (permissions.contains(o1.toString())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}
