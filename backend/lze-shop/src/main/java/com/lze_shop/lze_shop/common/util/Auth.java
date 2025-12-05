package com.lze_shop.lze_shop.common.util;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lze_shop.lze_shop.common.classes.Config;

@Component
public class Auth {

    private static Config staticConfig;

    @Autowired
    private Config config;

    // 在类初始化后，将实例注入到静态变量
    @Autowired
    public void init() {
        staticConfig = config;
    }

    // 静态方法可以直接调用
    public static String genJwt(String id){
        String secretKey = staticConfig.getSecretKey();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        
        String jti = UUID.randomUUID().toString();
        long expirationTime = System.currentTimeMillis() + 3600_000; // 1小时后过期

        return JWT.create()
                .withClaim("id", id)
                .withClaim("jti", jti)
                .withExpiresAt(new Date(expirationTime))
                .sign(algorithm);
    }

    // 获取uid
    public static String getUid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    // 获取过期时间(秒级)1个月过期
    public static long getExpireTime(){
        return System.currentTimeMillis()/1000 + (3600*24*30);
    }
}
