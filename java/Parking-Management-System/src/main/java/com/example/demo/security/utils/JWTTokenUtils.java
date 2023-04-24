package com.example.demo.security.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.demo.security.Service.SysUserDetailService;
import com.example.demo.security.config.JWTConfig;
import com.example.demo.security.entity.SysUserDetial;
import com.example.demo.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.example.demo.security.config.JWTConfig.refreshTime;

@Slf4j
@Component
public class JWTTokenUtils {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static JWTTokenUtils jwtTokenUtils;
    @Autowired
    private SysUserDetailService sysUserDetailService;
    public static String createToken(SysUserDetial userDetial) {
        String token = Jwts.builder()
                .setId(userDetial.getId().toString())
                .setSubject(userDetial.getUsername())
                .setIssuedAt(new Date())
                .setIssuer("fundebug")
                .setExpiration(new Date(System.currentTimeMillis() + JWTConfig.expiration))
                .signWith(SignatureAlgorithm.HS512, JWTConfig.secret)
                .claim("authorities", null).compact();
        return JWTConfig.tokenPrefix + token;
    }
    public static void setTokenRedis(String token,String username){
        token=token.substring(JWTConfig.tokenPrefix.length());
        Integer refreshtime= refreshTime;
        LocalDateTime localDateTime=LocalDateTime.now();
        RedisUtils.hset(token, "username", username, refreshTime);
        RedisUtils.hset(token, "refreshTime",
                dateTimeFormatter.format(localDateTime.plus(refreshTime, ChronoUnit.MILLIS)), refreshTime);
        RedisUtils.hset(token, "expiration", dateTimeFormatter.format(localDateTime.plus(JWTConfig.expiration, ChronoUnit.MILLIS)),
                refreshTime);
    }
    public static SysUserDetial parseToken(String token) {
        SysUserDetial userDetial = null;
        if (StringUtils.isNotEmpty(token)) {
            try {
                token = token.substring(JWTConfig.tokenPrefix.length());
                Claims claims = Jwts.parser().setSigningKey(JWTConfig.secret).parseClaimsJws(token).getBody();
                userDetial = new SysUserDetial();
                userDetial.setId(Long.parseLong(claims.getId()));
                userDetial.setUsername(claims.getSubject());
                Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
                String authority = claims.get("authorities").toString();
                if (StringUtils.isNotEmpty(authority)) {
                    List<Map<String, String>> authorityList = JSON.parseObject(authority,
                            new TypeReference<List<Map<String, String>>>() {
                            });
                    for (Map<String, String> role : authorityList) {
                        if (!role.isEmpty()) {
                            authorities.add(new SimpleGrantedAuthority(role.get("authority")));
                        }
                    }
                }
                userDetial.setAuthorities(authorities);
            } catch (Exception e) {
                log.error("解析Token异常：" + e);
            }
        }
        return userDetial;
    }
    public static void deleteToken(String token){
        if(StringUtils.isNotEmpty(token)){
            token=token.substring(JWTConfig.tokenPrefix.length());
            RedisUtils.deleteKey(token);
        }
    }
    public static boolean hasToken(String token) {
        if (StringUtils.isNotEmpty(token)) {
            token = token.substring(JWTConfig.tokenPrefix.length());
            return RedisUtils.hasKey(token);
        }
        return false;
    }
    public static void addBlackList(String token) {
        if (StringUtils.isNotEmpty(token)) {
            token = token.substring(JWTConfig.tokenPrefix.length());
            RedisUtils.hset("blackList", token, dateTimeFormatter.format(LocalDateTime.now()));
        }
    }
    public static boolean isBlackList(String token) {
        if (StringUtils.isNotEmpty(token)) {
            token = token.substring(JWTConfig.tokenPrefix.length());
            return RedisUtils.hasKey("blackList", token);
        }
        return false;
    }
    public static boolean isExpiration(String expiration) {
        LocalDateTime expirationTime = LocalDateTime.parse(expiration, dateTimeFormatter);
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.compareTo(expirationTime) > 0) {
            return true;
        }
        return false;
    }
    public static boolean isValid(String refreshTime) {
        LocalDateTime validTime = LocalDateTime.parse(refreshTime, dateTimeFormatter);
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.compareTo(validTime) > 0) {
            return false;
        }
        return true;
    }
    public static String getExpirationByToken(String token) {
        if (StringUtils.isNotEmpty(token)) {
            // 去除JWT前缀
            token = token.substring(JWTConfig.tokenPrefix.length());
            return RedisUtils.hget(token, "expiration").toString();
        }
        return null;
    }
    public static String getRefreshTimeByToken(String token) {
        if (StringUtils.isNotEmpty(token)) {
            // 去除JWT前缀
            token = token.substring(JWTConfig.tokenPrefix.length());
            return RedisUtils.hget(token, "refreshTime").toString();
        }
        return null;
    }
    public static String getUserNameByToken(String token) {
        if (StringUtils.isNotEmpty(token)) {
            token = token.substring(JWTConfig.tokenPrefix.length());
            return RedisUtils.hget(token, "username").toString();
        }
        return null;
    }
    public static String refreshAccessToken(String oldToken) {
        String username = JWTTokenUtils.getUserNameByToken(oldToken);
        SysUserDetial sysUserDetial=(SysUserDetial) jwtTokenUtils.sysUserDetailService.loadUserByUsername(username);
        //sysUserDetails.setIp(JWTTokenUtils.getIpByToken(oldToken));
        return createToken(sysUserDetial);
    }
}
