package com.joorani.myshop.common.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class LogTraceAspect {

    @Pointcut("execution(* com.joorani.myshop.controller.*.*(..))")
    public void onRequest() {
    }

    @Pointcut("execution(* *..*Repository.*(..))")
    public void jpaQuery() {
    }

    @Around("onRequest()")
    public Object doApiTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Map<String, Object> params = new LinkedHashMap<>();

        try {

            return joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long runTime = endTime - startTime;

            params.put("request_url", request.getRequestURL());
            params.put("request_method", request.getMethod());
            params.put("class_name", joinPoint.getSignature().getDeclaringType().getSimpleName());
            params.put("latency_time", runTime);

            log.info("params: {}", params);
        }
    }

    @Around("jpaQuery()")
    public Object doQueryTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        try {
            return joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long runTime = endTime - startTime;

            log.info("class name={}, Query_latency_time={}", joinPoint.getSignature().getDeclaringType().getSimpleName(), runTime);
        }
    }

}
