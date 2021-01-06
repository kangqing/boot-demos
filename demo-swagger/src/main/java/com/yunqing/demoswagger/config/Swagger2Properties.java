package com.yunqing.demoswagger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger2 配置
 * @author kangqing
 * @since 2020/12/30 13:50
 */
@Configuration
@ConfigurationProperties(prefix = "swagger2")
@Data
public class Swagger2Properties {

	/**
	 * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
	 */
	private Boolean enable;

	/**
	 * 标题
	 */
	private String title;
	/**
	 * 基础包
	 */
	private String basePackage;
	/**
	 * 版本
	 */
	private String version;
	/**
	 * 联系人姓名
	 */
	private String contractName;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 联系人Url
	 */
	private String contractUrl;
	/**
	 * 联系人邮箱
	 */
	private String contractEmail;

	/**
	 * 接口调试地址
	 */
	private String tryHost;

}
