package com.woniuxy.cq.ssmboot39.component;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Slf4j
@Component
public class WebLogAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void reqPointcut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postPointcut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getPointcut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deletePointcut() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putPointcut() {
    }

    @Pointcut("reqPointcut() || postPointcut() || getPointcut() || deletePointcut() || putPointcut()")
    public void logPointcut() {
    }

    @Around("logPointcut()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //启动计时器
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //执行请求
        Object result = joinPoint.proceed();
        //停止计时器
        stopWatch.stop();
        //打印
        log.debug("--==>请求{} [{}]-处理方法{}-参数为：{}-执行时间 {} ms 请求来源IP：{}",
                request.getRequestURI(),
                request.getMethod(),
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                //从请求中获取参数，不能处理@RequestBody的情况，使用controller的java方法参数可以
                // 但是参数绑定异常时无法处理，考虑到主要是为了记录正常请求，选择方法参数
                Arrays.toString(joinPoint.getArgs()),
                stopWatch.getTotalTimeMillis(),
                getIPAddress(request));

        return result;
    }

    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;
        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }
        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }
        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}
