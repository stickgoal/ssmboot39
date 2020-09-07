package com.woniuxy.cq.ssmboot39.controller.component;

import com.woniuxy.cq.ssmboot39.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 将所有controller视作一个切面，提供一些功能
 * 比如全局异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e, HttpServletResponse response){
        log.warn("全局异常拦截",e);

      return Result.fail("SYS_ERROR","系统出错："+e.getMessage());
    }

}
