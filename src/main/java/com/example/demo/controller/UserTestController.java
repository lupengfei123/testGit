package com.example.demo.controller;

import com.example.demo.entity.UserTest;
import com.example.demo.service.impl.UserTestServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userTest")

public class  UserTestController {
    @Resource
    private UserTestServiceImpl userTestServiceImpl;



    @GetMapping("/detail")
    public UserTest detail(@RequestParam("userId")String userId) throws Exception {
    return  userTestServiceImpl.detail(userId);
    }

}
