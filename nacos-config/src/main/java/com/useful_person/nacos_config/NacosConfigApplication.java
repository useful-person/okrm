package com.useful_person.nacos_config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.useful_person.nacos_config.model.NacosConfigInfo;

@SpringBootApplication
@EnableConfigurationProperties(NacosConfigInfo.class)
public class NacosConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);
    }

}
