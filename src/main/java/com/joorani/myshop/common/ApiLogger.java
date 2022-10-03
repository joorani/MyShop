package com.joorani.myshop.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Slf4j
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApiLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String className, long responseTime) {
        log.debug("[{}], [RequestURL: {}], class name: {}, response time: {} mills", uuid, requestURL, className, responseTime);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
    }

    @PreDestroy
    public void close() {
    }
}
