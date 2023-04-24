package com.example.demo.security.Handler;

import com.example.demo.security.config.JWTConfig;
import com.example.demo.security.utils.JWTTokenUtils;
import com.example.demo.utils.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String token=httpServletRequest.getHeader(JWTConfig.tokenHeader);
        JWTTokenUtils.deleteToken(token);
        ResponseUtils.responseJson(httpServletResponse,ResponseUtils.response(200,"登出成功",null));
    }
}
