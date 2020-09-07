package com.woniuxy.cq.ssmboot39.controller;

import com.woniuxy.cq.ssmboot39.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {

    @PostMapping("login")
    public Result doLogin(String username, String password) {
        log.info("登录操作：{}",username);

        Subject currUser = SecurityUtils.getSubject();
        try {
            currUser.login(new UsernamePasswordToken(username, password));
            return Result.success();
        } catch (AuthenticationException e) {
            //debug info warn error
            log.info("登录失败",e);
            return Result.fail("AUTH_ERROR","用户名或者密码错误");
        }
    }

}
