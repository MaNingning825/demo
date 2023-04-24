package com.example.demo.security.config;

import com.example.demo.security.Handler.*;
import com.example.demo.security.Service.UserAuthenticationProvider;
import com.example.demo.security.Service.UserPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import com.example.demo.security.filter.JWTAuthenticationFilter;
import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserAccessDeniedHandler userAccessDeniedHandler;
    @Autowired
    private UserNotLoginHandler userNotLoginHandler;
    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;
    @Autowired
    private UserLoginFailureHandler userLoginFailureHandler;
    @Autowired
    private UserLogoutSuccessHandler userLogoutSuccessHandler;
    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;
    @Autowired
    private UserPermissionEvaluator userPermissionEvaluator;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(userAuthenticationProvider);
    }
    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(userPermissionEvaluator);
        return handler;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("白名单"+JWTConfig.whiteList);
        http.authorizeRequests()
                .antMatchers(JWTConfig.whiteList.split(",")).permitAll()
                .anyRequest().permitAll()
//                .anyRequest().authenticated()
                .and().httpBasic().authenticationEntryPoint(userNotLoginHandler)
                .and().formLogin().loginProcessingUrl("/login/submit")
                .successHandler(userLoginSuccessHandler)
                .failureHandler(userLoginFailureHandler)
                .and().logout().logoutUrl("/logout/submit")
                .logoutSuccessHandler(userLogoutSuccessHandler).deleteCookies("JSESSIONID")
                .and().exceptionHandling().accessDeniedHandler(userAccessDeniedHandler)
                .and().cors()
                .and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().cacheControl().and().frameOptions().disable();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
    }
}
