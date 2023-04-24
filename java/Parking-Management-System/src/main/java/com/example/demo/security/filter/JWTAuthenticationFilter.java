package com.example.demo.security.filter;

import com.example.demo.security.config.JWTConfig;
import com.example.demo.security.entity.SysUserDetial;
import com.example.demo.security.utils.JWTTokenUtils;
import com.example.demo.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String token = request.getHeader(JWTConfig.tokenHeader);
        if (token != null && token.startsWith(JWTConfig.tokenPrefix)) {
            if (JWTTokenUtils.isBlackList(token)) {
                ResponseUtils.responseJson(response, ResponseUtils.response(505, "Token已失效", "Token已进入黑名单"));
                return;
            }
            if (JWTTokenUtils.hasToken(token)) {
                String expiration = JWTTokenUtils.getExpirationByToken(token);
                String username = JWTTokenUtils.getUserNameByToken(token);
                if (JWTTokenUtils.isExpiration(expiration)) {
                    JWTTokenUtils.addBlackList(token);
                    String validTime = JWTTokenUtils.getRefreshTimeByToken(token);
                    if (JWTTokenUtils.isValid(validTime)) {
                        String newToke = JWTTokenUtils.refreshAccessToken(token);
                        JWTTokenUtils.deleteToken(token);
                        JWTTokenUtils.setTokenRedis(newToke, username);
                        response.setHeader(JWTConfig.tokenHeader, newToke);

                        log.info("用户{}的Token已过期，但为超过刷新时间，已刷新", username);

                        token = newToke;
                    } else {
                        log.info("用户{}的Token已过期且超过刷新时间，不予刷新", username);
                        JWTTokenUtils.addBlackList(token);
                        ResponseUtils.responseJson(response, ResponseUtils.response(505, "Token已过期", "已超过刷新有效期"));
                        return;
                    }
                }
                SysUserDetial sysUserDetial=JWTTokenUtils.parseToken(token);
                if (sysUserDetial != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            sysUserDetial, sysUserDetial.getId(), sysUserDetial.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        if(token != null && token.startsWith(JWTConfig.tokenPrefix)){
            if (JWTTokenUtils.hasToken(token)) {
                SysUserDetial sysUserDetial=JWTTokenUtils.parseToken(token);
                if (sysUserDetial != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            sysUserDetial, sysUserDetial.getId(), sysUserDetial.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
