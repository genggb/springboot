package com.ggb.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "hello")
@PropertySource(value = {"classpath:config/hello.properties"})
@Getter
@Setter
public class HelloConfig {

    private String name;
    private String msg;
}
