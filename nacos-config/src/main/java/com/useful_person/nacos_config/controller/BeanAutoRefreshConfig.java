package com.useful_person.nacos_config.controller;

import com.alibaba.fastjson2.JSON;
import com.useful_person.nacos_config.model.NacosConfigInfo;
import com.useful_person.nacos_config.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/nacos")
public class BeanAutoRefreshConfig {

    private NacosConfigInfo nacosConfigInfo;

    private SecurityProperties securityProperties;

    @GetMapping("/bean")
    public Map<String, String> getConfigInfo() {
        Map<String, String> result = new HashMap<>();
        result.put("serverAddr", nacosConfigInfo.getServerAddr());
        result.put("prefix", nacosConfigInfo.getPrefix());
        result.put("group", nacosConfigInfo.getGroup());
        result.put("namespace", nacosConfigInfo.getNamespace());
        result.put("security", JSON.toJSONString(securityProperties));
        return result;
    }
}
