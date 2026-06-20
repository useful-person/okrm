package com.useful_person.okrm.user_service;

import com.useful_person.nacos_config.model.NacosConfigInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.useful_person.nacos_config.properties.SecurityProperties;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableConfigurationProperties(SecurityProperties.class)
//@EnableConfigurationProperties(NacosConfigInfo.class)
@EnableConfigurationProperties
public class UserServiceApplication {

    /**
     * 应用主入口，启动Spring Boot服务
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
