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

        
        String jti = genJti();
        long exp=getExp();

        return JWT.create()
                .withClaim("id", id)
                .withClaim("jti", jti)
                .withClaim("exp", exp)
                .sign(algorithm);
    }
    // 生成jti
    public static String genJti() {
       String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        long timestamp = System.currentTimeMillis();
        sb.append("_").append(timestamp);
        return sb.toString();
    }
    // 获取uid
    public static String getUid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    // 获取过期时间(秒级)1个月过期
    public static long getExp(){
        return System.currentTimeMillis()/1000 + (3600*24*30);
    }
}
