package com.useful_person.nacos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NacosConfigRefreshListener {

    private static final Logger logger = LoggerFactory.getLogger(NacosConfigRefreshListener.class);

    @EventListener(EnvironmentChangeEvent.class)
    public void onEnvironmentChange(EnvironmentChangeEvent event) {
        logger.info("Nacos config changed keys: {}", event.getKeys());
    }
}
