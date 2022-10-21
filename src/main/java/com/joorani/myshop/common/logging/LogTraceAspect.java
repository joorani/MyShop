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

            params.put("Request url", request.getRequestURL());
            params.put("Request Method", request.getMethod());
            params.put("Class Name", joinPoint.getSignature().getDeclaringType().getName());
            params.put("Method", joinPoint.getSignature().getName());
            params.put("Latency time", runTime +" mills");

            log.info("params: {}", params);
        }
    }

    @Around("jpaQuery()")
    public Object doQueryTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Map<String, Object> params = new LinkedHashMap<>();
        try {
            return joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long runTime = endTime - startTime;

            params.put("Class Name", joinPoint.getSignature().getDeclaringType().getName());
            params.put("Method Name", joinPoint.getSignature().getName());
            params.put("Query Latency time", runTime + " mills");

            log.info("params: {}", params);
        }
    }

}
