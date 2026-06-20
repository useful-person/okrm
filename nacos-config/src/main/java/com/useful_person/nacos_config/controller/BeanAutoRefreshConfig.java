package com.useful_person.nacos_config.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson2.JSON;
import com.useful_person.nacos_config.model.NacosConfigInfo;
import com.useful_person.nacos_config.properties.SecurityProperties;

@RestController
@RequestMapping("/nacos")
public class BeanAutoRefreshConfig {
    @Autowired
    private NacosConfigInfo nacosConfigInfo;

    @Autowired
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
