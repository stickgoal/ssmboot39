package com.woniuxy.cq.ssmboot39.service.impl;

import com.woniuxy.cq.ssmboot39.dao.UserMapper;
import com.woniuxy.cq.ssmboot39.entity.User;
import com.woniuxy.cq.ssmboot39.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
