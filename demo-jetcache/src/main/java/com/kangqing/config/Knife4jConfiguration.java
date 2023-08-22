/*
package com.kangqing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

*/
/**
 * OpenAPI3的规范，目前针对Java的Spring Boot项目，主要支持的有2个版本
 *
 * springfox 3.0.0： 同时兼容OpenAPI2以及OpenAPI3，但是停更很久了
 * springdoc-openapi： 兼容OpenAPI3规范，更新速度频繁
 * Knife4j在只有的OpenAPI3规范中，底层基础框架选择springdoc-openapi项目
 *
 * 针对Springfox3.0.0版本会放弃。
 *
 * 建议开发者如果使用OpenAPI3规范的话，也尽快迁移过来。
 * @author kangqing
 * @since 2023/8/22 11:08
 *//*

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        //指定使用Swagger2规范
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //描述字段支持Markdown语法
                        .description("# Knife4j RESTful APIs")
                        .termsOfServiceUrl("https://www.yunqing.xyz/")
                        .contact("xxxxxx@gmail.com")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("jetcache缓存服务")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.kangqing.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
*/
