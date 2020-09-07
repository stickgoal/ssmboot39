package com.woniuxy.cq.ssmboot39.component;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
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
    public void reqPointcut(){};
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postPointcut(){};
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getPointcut(){};
    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deletePointcut(){};
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putPointcut(){};

    @Pointcut("reqPointcut() || postPointcut() || getPointcut() || deletePointcut() || putPointcut()")
    public void logPointcut(){};

    @Before("logPointcut()")
    public void log(JoinPoint joinPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.debug(">>URL : " + request.getRequestURL().toString());
        log.debug(">>HTTP_METHOD : " + request.getMethod());
        log.debug(">>IP : " + request.getRemoteAddr());
        log.debug(">>CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        Map<String, String[]> parameterMap = request.getParameterMap();
        if(parameterMap!=null&&parameterMap.size()>0) {
            Map<String, String> printMap = new HashMap<>();

            parameterMap.forEach((k, v) -> {
                printMap.put(k, v.length == 1 ? v[0] : Arrays.toString(v));
            });
            log.debug(">>ARGS : " + (printMap));
        }else {
            log.debug(">>ARGS : []");
        }


    }





}
