package com.example.demo.security.Handler;

import com.example.demo.security.entity.SysUserDetial;
import com.example.demo.security.utils.JWTTokenUtils;
import com.example.demo.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SysUserDetial sysUserDetial=(SysUserDetial) authentication.getPrincipal();
        String token= JWTTokenUtils.createToken(sysUserDetial);
        System.out.println("-------------------------"+token+"----------------");
        JWTTokenUtils.setTokenRedis(token,sysUserDetial.getUsername());
        Map<String,String> tokenmap=new HashMap<>();
        tokenmap.put("token",token);
        ResponseUtils.responseJson(httpServletResponse,ResponseUtils.response(200,"登录成功",tokenmap));
    }
}
