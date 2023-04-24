package com.example.demo.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
@SuppressWarnings("static-access")
public class JWTConfig {
    /*密匙*/
    public static String secret;
    /*头部*/
    public static String tokenHeader;
    /*前缀*/
    public static String tokenPrefix;
    /*过期时间*/
    public static Integer expiration;
    /*有效时间*/
    public static Integer refreshTime;
    /*白名单*/
    public static String whiteList;
    public static String antMatchers;

    /*毫秒*/
    public void setExpiration(Integer e) {
        this.expiration = e * 1000;
    }
    public void setRefreshTime(Integer i){
        this.refreshTime=i*24*3600*1000;
    }
    public void setSecret(String s) {
        this.secret = s;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix + " ";
    }
    public void setWhiteList(String whiteList){
        this.whiteList=whiteList;
    }
}
