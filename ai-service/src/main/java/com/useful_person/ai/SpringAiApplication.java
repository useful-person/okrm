package com.useful_person.ai;

import java.time.Instant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class SpringAiApplication {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        SpringApplication.run(SpringAiApplication.class, args);

        long cost = System.currentTimeMillis() - start;
        log.info("✅ Spring AI Application started at {} (耗时 {} ms)", Instant.now(), cost);
    }
}
