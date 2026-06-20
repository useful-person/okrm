package com.useful_person.nacos_config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ConfigListener {

    /**
     * Nacos dataId.
     */
    public static final String DATA_ID = "nacos-gateway";

    /**
     * Nacos group.
     */
    public static final String GROUP = "DEFAULT_GROUP";

    private final NacosConfigManager nacosConfigManager;

    @PostConstruct
    public void init() throws NacosException {
        ConfigService configService = nacosConfigManager.getConfigService();

        configService.addListener(DATA_ID, GROUP, new Listener() {
            @Override
            public Executor getExecutor() {
                return Executors.newSingleThreadExecutor();
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                log.info("[dataId]:[{}],Configuration changed to: {}", DATA_ID, configInfo);
            }
        });
    }

}
