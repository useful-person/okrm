package com.useful_person.okrm.user_service.controller;

import com.useful_person.nacos_config.properties.SecurityProperties;
import com.useful_person.okrm.user_service.dao.UserRepository;
import com.useful_person.okrm.user_service.domain.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/demo")
public class UserServiceController {

    private final UserRepository userRepository;

    private SecurityProperties securityProperties;

    @PostMapping(path = "/add")
    public  String addNewUser(@RequestParam String username, @RequestParam String email) {
        UserInfo n = new UserInfo();
        n.setUsername(username);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public Iterable<UserInfo> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}
