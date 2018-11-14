package com.yang.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.only.mate.springboot.aop")
@EnableAspectJAutoProxy//开启AspectJ注解
public class CustomAopConfigurer {
}
