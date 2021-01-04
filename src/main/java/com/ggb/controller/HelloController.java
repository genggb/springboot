package com.ggb.controller;

import com.ggb.config.HelloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 功能描述:
 * 第一部分：spring-boot入门
 * 1、@RestController = @Controller + @ResponseBody
 * 2、lombok需要引入jar包并且安装插件才可以使用（@Slf4j、@Getter、@Setter）
 * 3、@Component和@Configuration：表明当前类是一个 Java Bean，可以直接注入
 * 4、@ConfigurationProperties(prefix = “hello”)：表示获取前缀为 hello 的配置信息
 * 5、@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})：表示可以先不配数据库信息启动
 * 6、@ComponentScan(basePackages={"com.ggb.*"})：需要扫描的controller包路径
 * 7、@PropertySource(value = {"classpath:hello.properties"})：获取指定配置文件
 * 8、application.yml配置端口和地址
 * 9、application.yml配置ssl，支持https(https://localhost:9080/demo/hello)
 * 10、banner.txt自定义启动日志输出（一般用于输出系统名和版本）
 * 11、logback-spring.xml文件配置可输出日志文件
 * @Author: genggb
 * @Date: 2020-1-20 16:18
 */
@RestController
@Slf4j
public class HelloController {

    @Autowired
    private HelloConfig helloConfig;

    @RequestMapping("/hello")
    public String hello(){
        log.info("进入hello");
        return helloConfig.getName()+helloConfig.getMsg();
    }
}
