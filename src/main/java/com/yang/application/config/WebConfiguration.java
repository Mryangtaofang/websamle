package com.yang.application.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


@Configuration
@Slf4j
@Order(1)
public class WebConfiguration {

    static {
        log.info("加载WebConfiguration...........");
    }

//    @Bean
//    public String yangyaming() {
//        log.info("初始化yangyaming...........");
//        return "yangyaming";
//    }
}
