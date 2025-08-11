package com.useful_person.okrm.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson2.JSON;
import com.useful_person.nacos.properties.SecurityProperties;
import com.useful_person.okrm.user_service.dao.UserRepository;
import com.useful_person.okrm.user_service.domain.UserInfo;

@Controller
@RequestMapping(path = "/demo")
public class UserServiceController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityProperties securityProperties;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestParam String username, @RequestParam String email) {
        UserInfo n = new UserInfo();
        n.setUsername(username);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<UserInfo> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path = "/config")
    public @ResponseBody String getConfig() {

        return JSON.toJSONString(securityProperties);
    }
}
