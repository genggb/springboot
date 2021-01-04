package com.ggb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 功能描述:Swagger2 Config Bean
 * 访问地址：http://ip:port/server/swagger-ui.html
 * @Author: genggb
 * @Date: 2020-1-20 17:08
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ggb"))//需要可视化api的包
                .paths(PathSelectors.any())
                .build()
//                .enable(false)//是否启用Swagger，项目上线可以禁用
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot使用Swagger构建api文档")
                .description("简单优雅的 restfun 风格")
                .termsOfServiceUrl("http://192.168.0.56:9080/xmgl/dxtLogin.jsp")
                .version("1.0")
                .build();
    }
}
