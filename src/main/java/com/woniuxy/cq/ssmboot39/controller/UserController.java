package com.woniuxy.cq.ssmboot39.controller;

import com.woniuxy.cq.ssmboot39.entity.User;
import com.woniuxy.cq.ssmboot39.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("getById")
    public User  findById(int userId, Date date){
        logger.info("{},{}",userId,date);
        return userService.findById(userId);
    }




}
