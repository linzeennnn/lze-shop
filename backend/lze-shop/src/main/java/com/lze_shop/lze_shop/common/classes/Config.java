package com.lze_shop.lze_shop.common.classes;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "lze-shop")
@Data
public class Config {
    private String secretKey;
    private int maxSize;
}
