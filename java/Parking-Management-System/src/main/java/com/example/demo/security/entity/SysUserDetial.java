package com.example.demo.security.entity;

import com.example.demo.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserDetial extends SysUser implements UserDetails, Serializable {
    private static final long serialVersionUID=1L;
    private Collection<GrantedAuthority> authorities;
    private boolean isAccountNonExpired=false;
    private boolean isAccountNonLocked=false;
    private boolean isCredentialsNonExpired=false;
    private boolean isEnabled=false;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
